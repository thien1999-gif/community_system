package edu.fa.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.fa.config.JsonReader;
import edu.fa.dto.CustomCourse;
import edu.fa.model.Course;
import edu.fa.model.Subject;
import edu.fa.model.Trainee;
import edu.fa.model.Trainer;
import edu.fa.service.CourseService;
import edu.fa.service.SubjectService;
import edu.fa.service.TraineeService;
import edu.fa.service.TrainerService;
import edu.fa.util.SecurityUtils;

//class controls flow of trainee code
@Controller
public class TraineeController {

	@Autowired
	private TraineeService traineeService;
	@Autowired
	private TrainerService trainerService;
	@Autowired
	SubjectService subjectService;
	@Autowired
	CourseService courseService;
	@Autowired
	ServletContext servletContext;

	// show dashboard of trainee
	@RequestMapping(value = "/trainee/trainee-Home")
	public String traineeDashboard(Model model,
			@RequestParam(required = false, name = "subjectID", defaultValue = "0") int subjectID,
			@RequestParam(required = false, name = "curPage", defaultValue = "1") int curPage,
			HttpServletRequest request) {
		// map contain infor of trainer and subject
		try {
			Map<Trainer, Map<String, List<Subject>>> result = new HashMap<Trainer, Map<String, List<Subject>>>();

			String email = SecurityUtils.getPrincipal().getUsername();
			model.addAttribute("availSubject", traineeService.getSubjectList(email));

			// list trainer
			List<Trainer> list = traineeService.getTrainerProfileList(email, subjectID, curPage);
			// get infor trainer save in list
			for (Trainer trainer : list) {
				List<Subject> subjectList = trainerService.getSubjectList(trainer.getEmail());
				String score = trainerService.sumRatingScoreByTrainerID(trainer.getEmail());
				Map<String, List<Subject>> subList = new HashMap<String, List<Subject>>();
				subList.put(score, subjectList);
				result.put(trainer, subList);
			}

			// paging
			int totalPage = traineeService.countTrainerProfile(email, subjectID);

			// sent data to client
			model.addAttribute("trainerProfileList", result);
			model.addAttribute("subjectID", subjectID);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("curPage", curPage);

			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_dashboard";
	}

	// View profile of student
	@RequestMapping(value = "/trainee/viewProfile", method = RequestMethod.GET)
	public String traineeViewOwnProfileGET(Model model, ModelMap mm, HttpServletRequest request) {
		try {
			String email = SecurityUtils.getPrincipal().getUsername();
			Trainee trainee = traineeService.getInfoTrainee(email);
			mm.put("traineeProfile", trainee);
			// get data of student
			String codeAddress = trainee.getCodeAddress();
			String[] arrayCode = codeAddress.split(",");
			String codeVillage = arrayCode[0].trim();
			String codeWard = arrayCode[1].trim();
			String codeDistrict = arrayCode[2].trim();
			String codeProvince = arrayCode[3].trim();

			// sent data to client
			model.addAttribute("listProvince", traineeService.getListProvince());
			model.addAttribute("listDistrict", traineeService.getListDistrictByProvinceId(codeProvince));
			model.addAttribute("listWard", traineeService.getListWardByDistrictId(codeDistrict));
			model.addAttribute("listVillage", traineeService.getListVillageByWardId(codeWard));

			model.addAttribute("codeProvince", codeProvince);
			model.addAttribute("codeDistrict", codeDistrict);
			model.addAttribute("codeWard", codeWard);
			model.addAttribute("codeVillage", codeVillage);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_viewProfile";
	}

	// Update Profile of student
	@RequestMapping(value = "/trainee/viewProfile", method = RequestMethod.POST)
	public String traineeViewOwnProfilePOST(@ModelAttribute("traineeProfile") Trainee trainee, Model model, ModelMap mm,
			@RequestParam String city, @RequestParam String district, @RequestParam String wards,
			@RequestParam String numberAddress, @RequestParam String avatar, @RequestParam String addressHidden,
			@RequestParam String seDistrict, @RequestParam String seProvince, @RequestParam String seWard,
			@RequestParam String seVillage, HttpServletRequest request

	) {
		try {
			// get data to update profile
			String codeAddress = seVillage + "," + seWard + "," + seDistrict + "," + seProvince;
			// mm.put("traineeProfile", traineeService.getInfoTrainee("1"));
			String email = SecurityUtils.getPrincipal().getUsername();
			trainee.setEmail(email);
			String address = "";

			address = numberAddress + ", " + wards + ", " + district + ", " + city + ", VietNam";

			// location

			trainee.setCodeAddress(codeAddress);
			trainee.setAddress(address);
			String coordinate = "";
			try {
				JSONObject json = JsonReader.readJsonFromUrl(address);
				System.out.println(json);

				JSONArray array = json.getJSONArray("features");
				json = new JSONObject(array.optString(0));
				coordinate = json.optString("center");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} // end

			// save data into database
			String[] coor = coordinate.split(",");
			double longitude = Double.parseDouble(coor[0].replace("[", ""));
			double latitude = Double.parseDouble(coor[1].replace("]", ""));

			trainee.setLongitude(longitude);
			trainee.setLatitude(latitude);

			// encript password by bcript
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			MultipartFile mul = trainee.getProfileImage();
			String nameAvatarTemp = bCryptPasswordEncoder.encode(mul.getOriginalFilename());
			String nameAvatar = nameAvatarTemp.replace("/", "");
			String Newavatar = "/traineeAssets/img/" + nameAvatar + mul.getOriginalFilename();

			// set Old avatar
			trainee.setAvatar(avatar);

			// set New avatar
			if (!mul.isEmpty()) {
				String fileName = servletContext.getRealPath("/") + "WEB-INF\\traineeAssets\\img\\" + nameAvatar
						+ mul.getOriginalFilename();

				System.out.println(fileName);
				trainee.setAvatar(Newavatar);

				try {

					mul.transferTo(new File(fileName));
				} catch (Exception e) {
					// TODO: handle exception

				}
			}
//		

			traineeService.UpdateProfile(trainee);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "redirect:/trainee/viewProfile";
	}

	// Trainer Profile + From Request
	@RequestMapping(value = "/trainee/trainerProfile", method = RequestMethod.GET)
	public String traineeViewTrainerProfileAndFormRequest(Model model, @RequestParam String trainerID, ModelMap mm,
			HttpServletRequest request) {
		try {
			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
			// get trainer profile
			Trainer trainer = trainerService.getProfile(trainerID);
			model.addAttribute("trainerInfo", trainer);
			// get list subject of teacher
			List<Subject> listSubject = trainerService.getSubjectList(trainerID);
			model.addAttribute("listSubject", listSubject);

			// sent data to client
			Course course = new Course();
			mm.put("modelCourse", course);
//giờ bận của giáo viên
			boolean MWFMorningT = trainerService.checkTrainerTeaching(trainerID, 1, 1); 
			boolean MWFAfternoonT = trainerService.checkTrainerTeaching(trainerID, 1, 2); 
 			boolean MWFEveningT = trainerService.checkTrainerTeaching(trainerID, 1, 3); 

			boolean TTSMorningT = trainerService.checkTrainerTeaching(trainerID, 2, 1);
			boolean TTSAfternoonT = trainerService.checkTrainerTeaching(trainerID, 2, 2);
			boolean TTSEveningT = trainerService.checkTrainerTeaching(trainerID, 2, 3);
//giờ bận của học sinh
			boolean MWFMorningC = traineeService.existCourseOfTrainee(emailStudent, 1, 1);
			boolean MWFAfternoonC = traineeService.existCourseOfTrainee(emailStudent, 1, 2);
			boolean MWFEveningC = traineeService.existCourseOfTrainee(emailStudent, 1, 3);

			boolean TTSMorningC = traineeService.existCourseOfTrainee(emailStudent, 2, 1);
			boolean TTSAfternoonC = traineeService.existCourseOfTrainee(emailStudent, 2, 2);
			boolean TTSEveningC = traineeService.existCourseOfTrainee(emailStudent, 2, 3);
//giờ bận tổng thể
			boolean MWFMorning = false;
			boolean MWFAfternoon = false;
			boolean MWFEvening = false;

			boolean TTSMorning = false;
			boolean TTSAfternoon = false;
			boolean TTSEvening= false;
			
			if(MWFMorningT || MWFMorningC) {
				MWFMorning = true;
			}
			if(MWFAfternoonT || MWFAfternoonC) {
				MWFAfternoon = true;
			}
			if(MWFEveningT || MWFEveningC) {
				MWFEvening = true;
			}
			if(TTSMorningT || TTSMorningC) {
				TTSMorning = true;
			}
			if(TTSAfternoonC || TTSAfternoonT) {
				TTSAfternoon = true;
			}
			if(TTSEveningT || TTSEveningC) {
				TTSEvening = true;
			}
			
			model.addAttribute("MWFMorning", MWFMorning);
			model.addAttribute("MWFEvening", MWFEvening);
			model.addAttribute("MWFAfternoon", MWFAfternoon);

			model.addAttribute("TTSMorning", TTSMorning);
			model.addAttribute("TTSEvening", TTSEvening);
			model.addAttribute("TTSAfternoon", TTSAfternoon);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_dashboard_trainerProfile";
	}

	// Trainer Profile + From Request
	@RequestMapping(value = "/trainee/trainerProfile", method = RequestMethod.POST)
	public String traineeViewTrainerProfileAndFormRequestPost(@ModelAttribute("modelCourse") Course course,
			@RequestParam String trainerID, Model model, HttpServletRequest request) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			// set traineeID
			String traineeID = SecurityUtils.getPrincipal().getUsername();
			course.setTraineeID(traineeID);
			course.setCreateDate(timestamp);
			course.setStatus(1);

			// Insert to db

			if (!trainerService.checkTrainerTeaching(course.getTrainerID(), course.getDayInWeek(),
					course.getTimeInDay())) {
				course = courseService.createCourse(course);
				model.addAttribute("SUCCESS", "Create success!");
				return "trainee/trainee_dashboard_trainerProfile";
			} else {

				model.addAttribute("FAILED", "The teacher has had a lesson, please choose another time");
				model.addAttribute("trainerID", traineeID);
				return "redirect:/trainee/trainerProfile";
			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
	}

	// Trainee Post
	@RequestMapping(value = "/trainee/traineePost")
	public String traineeViewTraineePostAndProfile(Model model, HttpServletRequest request) {
		try {
			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_dashboard_traineePost";
	}

	// Manage Class
	@RequestMapping(value = "/trainee/manageClass")
	public String traineeViewOwnClass(Model model, HttpServletRequest request) {
		try {
			String emailTrainee = SecurityUtils.getPrincipal().getUsername();
			// list course end
			List<Course> listCourseByStatusEnd = courseService.selectStatusByTraineeID(emailTrainee);
			List<CustomCourse> listCustomCourse = new ArrayList<CustomCourse>();
			Trainee trainee = traineeService.findTraineeByEmail(emailTrainee);
			List<Trainer> listTrainer = new ArrayList<Trainer>();
			Trainer trainer = new Trainer();
			// get data course and trainer
			for (Course course : listCourseByStatusEnd) {
				// trainerID is email
				trainer = traineeService.findByEmailTrainer(course.getTrainerID());

				Subject subject = subjectService.searchSubjectByID(course.getSubjectID());

				CustomCourse cus = new CustomCourse(course.getCourseID(), course.getTitle(), course.getDescription(),
						trainer.getEmail(), trainer.getFullName(), trainer.getAvatar(), trainee.getEmail(),
						trainee.getFullName(), trainee.getAvatar(), trainee.getAddress(), course.getTimeInDay(),
						course.getDayInWeek(), course.getStatus(), course.getSubjectID(), subject.getSubject(),
						course.getDeposit(), course.getCreateDate());

				listTrainer.add(trainer);
				listCustomCourse.add(cus);

			}
			// sent data to client
			model.addAttribute("listCustomCourse", listCustomCourse);
			model.addAttribute("listTrainer", listTrainer);

			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_course_manageClass";
	}

	// My course
	@RequestMapping(value = "/trainee/myCourse")
	public String traineeViewOwnClassByStatus_2(Model model, HttpServletRequest request) {
		try {
			String emailTrainee = SecurityUtils.getPrincipal().getUsername();

			List<Course> listCourseByStatusEnd = courseService.selectCourseFinished(emailTrainee);// list chua lop da
																									// hoan
																									// thanh

			List<CustomCourse> listCustomCourseDone = new ArrayList<CustomCourse>();

			Trainee traineeDone = traineeService.findTraineeByEmail(emailTrainee);

			List<Trainer> listTrainerDone = new ArrayList<Trainer>();

			Trainer trainerDone = new Trainer();

			// danh sach lop da hoan thanh
			for (Course course : listCourseByStatusEnd) {
				// trainerID is email
				trainerDone = traineeService.findByEmailTrainer(course.getTrainerID());

				Subject subjectDone = subjectService.searchSubjectByID(course.getSubjectID());

				CustomCourse cus = new CustomCourse(course.getCourseID(), course.getTitle(), course.getDescription(),
						trainerDone.getEmail(), trainerDone.getFullName(), trainerDone.getAvatar(),
						traineeDone.getEmail(), traineeDone.getFullName(), traineeDone.getAvatar(),
						traineeDone.getAddress(), course.getTimeInDay(), course.getDayInWeek(), course.getStatus(),
						course.getSubjectID(), subjectDone.getSubject(), course.getDeposit(), course.getCreateDate());

				listTrainerDone.add(trainerDone);
				listCustomCourseDone.add(cus);

			}

			List<Course> listCourseByStatusStarted = courseService.selectCourseBegin(emailTrainee);// list lop moi bat
																									// dau

			List<CustomCourse> listCustomCourseBegin = new ArrayList<CustomCourse>();

			Trainee traineeBegin = traineeService.findTraineeByEmail(emailTrainee);

			List<Trainer> listTrainerBegin = new ArrayList<Trainer>();

			Trainer trainerBegin = new Trainer();

			for (Course course2 : listCourseByStatusStarted) {
				// trainerID is email
				trainerBegin = traineeService.findByEmailTrainer(course2.getTrainerID());

				Subject subject = subjectService.searchSubjectByID(course2.getSubjectID());

				CustomCourse cus = new CustomCourse(course2.getCourseID(), course2.getTitle(), course2.getDescription(),
						trainerBegin.getEmail(), trainerBegin.getFullName(), trainerBegin.getAvatar(),
						traineeBegin.getEmail(), traineeBegin.getFullName(), traineeBegin.getAvatar(),
						traineeBegin.getAddress(), course2.getTimeInDay(), course2.getDayInWeek(), course2.getStatus(),
						course2.getSubjectID(), subject.getSubject(), course2.getDeposit(), course2.getCreateDate());

				listTrainerBegin.add(trainerBegin);
				listCustomCourseBegin.add(cus);

			}

			// list poat doi admin duyet
			List<Course> listCourseWaiting = courseService.selectPostWaiting(emailTrainee);// list posting

			List<CustomCourse> listCustomCourseWaitng = new ArrayList<CustomCourse>();

			@SuppressWarnings("unused")
			Trainee traineeWaiting = traineeService.findTraineeByEmail(emailTrainee);

			for (Course course3 : listCourseWaiting) {
				// trainerID is email

				Subject subject = subjectService.searchSubjectByID(course3.getSubjectID());

				CustomCourse cus = new CustomCourse(course3.getCourseID(), course3.getTitle(), course3.getDescription(),

						course3.getTimeInDay(), course3.getDayInWeek(), course3.getStatus(), course3.getSubjectID(),
						subject.getSubject(), course3.getDeposit(), course3.getCreateDate());

				listCustomCourseWaitng.add(cus);

			}
			model.addAttribute("listCustomCourseWaitng", listCustomCourseWaitng);

			model.addAttribute("listCustomCourseBegin", listCustomCourseBegin);
			model.addAttribute("listTrainerBegin", listTrainerBegin);

			model.addAttribute("listCustomCourseDone", listCustomCourseDone);
			model.addAttribute("listTrainerDone", listTrainerDone);
			System.out.println("vao my course");

			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_my_course";
	}

	// rating teacher
	@RequestMapping(value = "/trainee/rating")
	public String traineeRating(@RequestParam(required = false) int courseID, Model model,
			@RequestParam(required = false) String star, @RequestParam(required = false) String trainerID,
			HttpServletRequest request) {
		try {
			String traineeID = SecurityUtils.getPrincipal().getUsername();

			// Insert data into db
			if (star != null) {
				Course course = courseService.getCourse(courseID);
				course.setStatus(4);

				// dem so lan da hoc
				int times = courseService.countTimes(trainerID, traineeID);

				// diem rating hien tai (neu co)
				double ratingScoreAVG = trainerService.selectRating(traineeID, trainerID);

				double totalReatingAVG = ratingScoreAVG * times;

				int starValue = Integer.parseInt(star);

				double newRatingScore = (totalReatingAVG + starValue) / (times + 1);
				courseService.updateCourseStatus(course);

				trainerService.insert(trainerID, traineeID, newRatingScore);
			} else {
				model.addAttribute("errorRating", "Please rating again!!!");
			}

			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "forward:/trainee/manageClass";
	}

	// Create Post of student
	@RequestMapping(value = "/trainee/createPost", method = RequestMethod.GET)
	public String getTraineeCreatePost(Model model, ModelMap mm, HttpServletRequest request) {
		try {
			mm.put("modelCourse", new Course());
			model.addAttribute("listSubject", subjectService.showAll());

			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
			
			boolean MWFMorning = traineeService.existCourseOfTrainee(emailStudent, 1, 1);
			boolean MWFAfternoon = traineeService.existCourseOfTrainee(emailStudent, 1, 2);
			boolean MWFEvening = traineeService.existCourseOfTrainee(emailStudent, 1, 3);

			boolean TTSMorning = traineeService.existCourseOfTrainee(emailStudent, 2, 1);
			boolean TTSAfternoon = traineeService.existCourseOfTrainee(emailStudent, 2, 2);
			boolean TTSEvening = traineeService.existCourseOfTrainee(emailStudent, 2, 3);
		
			
			
			model.addAttribute("MWFMorning", MWFMorning);
			model.addAttribute("MWFEvening", MWFEvening);
			model.addAttribute("MWFAfternoon", MWFAfternoon);

			model.addAttribute("TTSMorning", TTSMorning);
			model.addAttribute("TTSEvening", TTSEvening);
			model.addAttribute("TTSAfternoon", TTSAfternoon);
			
			
			
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_course_createPost";
	}

	@RequestMapping(value = "/trainee/createPost", method = RequestMethod.POST)
	public String postTraineeCreatePost(@ModelAttribute(value = "modelCourse") Course course, Model model, ModelMap mm,
			HttpServletRequest request) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			// tempt data traineeID
			String traineeID = SecurityUtils.getPrincipal().getUsername();
			course.setTraineeID(traineeID);

			course.setCreateDate(timestamp);

			// Insert to db
			course = courseService.createCourse(course);
			model.addAttribute("listSubject", subjectService.showAll());
			model.addAttribute("listSubjectID", subjectService.getListSubjectID());
			mm.put("modelCourse", course);

			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
			System.out.println(traineeInfomation.getAvatar());

			model.addAttribute("SUCCESS", "Create Success!  Please wait admin verify!!!");
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "trainee/trainee_course_createPost";
	}

	// View Trainer Request
	@RequestMapping(value = "/trainee/viewTrainerRequests")
	public String traineeViewTrainerRequests(Model model, HttpServletRequest request) {
		try {
			String email = SecurityUtils.getPrincipal().getUsername();
			List<Course> listCourse = courseService.findByStatusAndTraineeID(1, email);
			model.addAttribute("listCourse", listCourse);

			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_course_trainerRequest";
	}

	// view list request of teachers
	@RequestMapping(value = "/trainee/viewListTrainerRequest")
	public String viewListTrainerRequest(Model model, ModelMap mm, @RequestParam int id, HttpServletRequest request) {
		try {
			// list teacher
			List<Trainer> listTrainer = trainerService.getListTrainerByCourseID(id);
			// sent data to client
			model.addAttribute("courseID", id);
			model.addAttribute("listTrainer", listTrainer);

			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_course_listTrainerRequest";
	}
	////////////////////////////////////////////////////////////////////////

	// view detail request of teacher
	@RequestMapping(value = "/trainee/viewTrainerRequestDetails", method = RequestMethod.GET)
	public String viewTrainerRequestDetails(Model model, @RequestParam String idTrainer, ModelMap mm,
			@RequestParam int courseID, HttpServletRequest request) {
		try {
			// get profile of teacher
			Trainer trainer = trainerService.getProfile(idTrainer);
			// sent data to client
			mm.put("trainer", trainer);
			model.addAttribute("courseID", courseID);

			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_course_trainerRequest_viewDetail";
	}

	@RequestMapping(value = "/trainee/viewTrainerRequestDetails", method = RequestMethod.POST)
	public String viewTrainerRequestDetailsPOST(Model model, @RequestParam String idTrainer, @RequestParam int courseID,
			ModelMap mm, HttpServletRequest request) {
		try {
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			Course course = courseService.getCourse(courseID);
			course.setTrainerID(idTrainer);
			course.setStatus(2);

			System.out.println(trainerService.existTraierRating(idTrainer, emailStudent));
			courseService.createCourse(course);
			courseService.deleteCourseFromTrainerRequest(courseID);

			if (!trainerService.existTraierRating(idTrainer, emailStudent)) {
				trainerService.insertTrainerRating(idTrainer, emailStudent);

			}

			Trainee traineeInfomation = new Trainee();

			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "redirect:/trainee/viewTrainerRequests";
	}

	// student deny request of teacher
	@RequestMapping(value = "/trainee/denyTrainerRequest")
	public String denyTrainerRequest(@RequestParam int courseID, @RequestParam String trainerID, Model model,
			HttpServletRequest request) {
		// delete request to db
		try {
			trainerService.deleteTeacherAndCourseFromTrainerRequest(courseID, trainerID);

			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "redirect:/trainee/viewListTrainerRequest?id=" + courseID;
	}

	// New post
	@RequestMapping(value = "/trainee/newPost")
	public String traineeViewNewPost(Model model, HttpServletRequest request) {
		try {
			Trainee traineeInfomation = new Trainee();
			String emailStudent = SecurityUtils.getPrincipal().getUsername();
			traineeInfomation = traineeService.selectInfo(emailStudent);
			model.addAttribute("traineeInfo", traineeInfomation);

			List<Course> listNewPost = courseService.selectNewPost();
			model.addAttribute("listPost", listNewPost);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainee/trainee_newpost";
	}
}
