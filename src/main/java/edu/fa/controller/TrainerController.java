package edu.fa.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import edu.fa.model.Trainee;
import edu.fa.model.Trainer;
import edu.fa.model.Users;
import edu.fa.service.CourseService;
import edu.fa.service.TraineeService;
import edu.fa.service.TrainerService;
import edu.fa.service.UserAuthenticateService;
import edu.fa.util.SHA;
import edu.fa.util.SecurityUtils;

// controller for trainer ==================================
@Controller
public class TrainerController {

	@Autowired
	CourseService courseService;

	@Autowired
	UserAuthenticateService userAuthenticateService;
	@Autowired
	private TrainerService trainerService;
	@Autowired
	private TraineeService traineeService;
	SHA sha = new SHA();
	@Autowired
	ServletContext servletContext;

	// show teacher dashboard
	@RequestMapping(value = "/trainer/trainer-Home")
	public String searchTrainer(Model model,
			@RequestParam(required = false, name = "subjectID", defaultValue = "0") int subjectID,
			@RequestParam(required = false, name = "curPage", defaultValue = "1") int curPage,
			HttpServletRequest request) {
		// get email
		try {
			String email = SecurityUtils.getPrincipal().getUsername();
			model.addAttribute("trainerSubject", trainerService.getSubjectList(email));
			// list course
			List<CustomCourse> list = trainerService.getTraineeCourseBySubject(email, subjectID, curPage);
			int totalPage = trainerService.countTraineeCourseBySubject(email, subjectID);
			// sent data to client
			model.addAttribute("traineePostList", list);
			model.addAttribute("subjectID", subjectID);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("curPage", curPage);
			model.addAttribute("trainer", trainerService.getProfile(email));
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainer/trainer_Dashboard";
	}

	// handling request of student sent for teacher
	@RequestMapping(value = "/trainer/RequestTraineeCourseAction")
	public String trainerRequestCourseProcess(Model model,
			@RequestParam(required = false, name = "courseID") int courseID, HttpServletRequest request) {
		try {
			String trainerID = SecurityUtils.getPrincipal().getUsername();
			boolean isUpdateSuccess = trainerService.sendRequestToTraineeCourse(trainerID, courseID);
			model.addAttribute("updateMessage",
					isUpdateSuccess ? "Your request has been sent successfully!" : "Fail to send request!");
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "forward:/trainer/trainer-Home";
	}

	// show list manager course of teacher
	@RequestMapping(value = "/trainer/trainer-Manage-RequestCourse")
	public String trainerRequestCourse(Model model,
			@RequestParam(required = false, name = "subjectID", defaultValue = "0") int subjectID,
			@RequestParam(required = false, name = "curPage", defaultValue = "1") int curPage,
			HttpServletRequest request) {
		try {
			String email = SecurityUtils.getPrincipal().getUsername();
			// get list subject
			model.addAttribute("trainerSubject", trainerService.getSubjectList(email));
			List<CustomCourse> list = trainerService.getTrainerRequestCourse(email, subjectID, curPage);
			// paging
			int totalPage = trainerService.countTrainerRequestCourse(email, subjectID);
			// sent data to client
			model.addAttribute("trainerRequestList", list);
			model.addAttribute("subjectID", subjectID);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("curPage", curPage);
			model.addAttribute("trainer", trainerService.getProfile(email));
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainer/trainer_manageCourse_requestCourse";
	}

	// cancel request's teacher sent for student
	@RequestMapping(value = "/trainer/CancelTrainerRequestAction")
	public String cancelTrainerRequestProcess(Model model,
			@RequestParam(required = false, name = "courseID") int courseID, HttpServletRequest request) {
		try {
			String trainerID = SecurityUtils.getPrincipal().getUsername();
			// handling cancel
			boolean isUpdateSuccess = trainerService.cancelTrainerRequest(trainerID, courseID);
			model.addAttribute("updateMessage",
					isUpdateSuccess ? "Your request has been removed successfully!" : "Fail to cancel request!");
			model.addAttribute("trainer", trainerService.getProfile(trainerID));
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "forward:/trainer/trainer-Manage-RequestCourse";
	}

	// show and handle infor course is teaching of teacher
	@RequestMapping(value = "/trainer/trainer-Manage-TeachingCourse")
	public String trainerTeachingCourse(Model model,
			@RequestParam(required = false, name = "subjectID", defaultValue = "0") int subjectID,
			@RequestParam(required = false, name = "curPage", defaultValue = "1") int curPage,
			HttpServletRequest request) {
		try {
			String email = SecurityUtils.getPrincipal().getUsername();
			// get list course
			model.addAttribute("trainerSubject", trainerService.getSubjectList(email));
			List<CustomCourse> list = trainerService.getTrainerTeachingCourse(email, subjectID, curPage);
			// paging
			int totalPage = trainerService.countTrainerTeachingCourse(email, subjectID);
			// sent data to client
			model.addAttribute("teachingCourseList", list);
			model.addAttribute("subjectID", subjectID);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("curPage", curPage);
			model.addAttribute("trainer", trainerService.getProfile(email));
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainer/trainer_manageCourse_teachingCourse";
	}

	// show and handle end course of teacher
	@RequestMapping(value = "/trainer/EndTeachingCourseAction")
	public String endTrainerRequestProcess(Model model, @RequestParam(required = false, name = "courseID") int courseID,
			HttpServletRequest request) {
		try {
			String trainerID = SecurityUtils.getPrincipal().getUsername();
			// handle end course
			boolean isUpdateSuccess = trainerService.endTeachingCourse(trainerID, courseID);
			// sent data to client
			model.addAttribute("updateMessage",
					isUpdateSuccess ? "Your course has been ended!" : "Fail to end course!");
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "forward:/trainer/trainer-Manage-TeachingCourse";
	}

	// show and handle student's request of teacher
	@RequestMapping(value = "/trainer/trainer-Manage-TraineeRequest")
	public String trainerTraineeRequest(Model model,
			@RequestParam(required = false, name = "subjectID", defaultValue = "0") int subjectID,
			@RequestParam(required = false, name = "curPage", defaultValue = "1") int curPage,
			HttpServletRequest request) {
		try {
			String email = SecurityUtils.getPrincipal().getUsername();
			// get list subject
			model.addAttribute("trainerSubject", trainerService.getSubjectList(email));
			List<CustomCourse> list = trainerService.getTraineeRequestCourse(email, subjectID, curPage);
			// paging
			int totalPage = trainerService.countTraineeRequestCourse(email, subjectID);
			// sent data to client
			model.addAttribute("traineeRequestList", list);
			model.addAttribute("subjectID", subjectID);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("curPage", curPage);
			model.addAttribute("trainer", trainerService.getProfile(email));
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "trainer/trainer_manageCourse_traineeRequest";
	}

	// show and handle deny student's request of teacher
	@RequestMapping(value = "/trainer/DenyTraineeRequestAction")
	public String denyTraineeRequest(Model model, @RequestParam(required = false, name = "courseID") int courseID,
			HttpServletRequest request) {
		try {
			String trainerID = SecurityUtils.getPrincipal().getUsername();
			// handle deny request
			boolean isUpdateSuccess = trainerService.denyTraineeRequest(trainerID, courseID);
			// sent data to client
			model.addAttribute("updateMessage", isUpdateSuccess ? "Deny successfully!" : "Fail to deny course!");
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "forward:/trainer/trainer-Manage-TraineeRequest";
	}

	// show and handle accept request of teacher
	@RequestMapping(value = "/trainer/AcceptTraineeRequestAction")
	public String acceptTraineeRequest(Model model, @RequestParam(required = false, name = "courseID") int courseID,
			HttpServletRequest request) {
		try {
			String trainerID = SecurityUtils.getPrincipal().getUsername();
			// handle accept request
			boolean isUpdateSuccess = trainerService.accpetTraineeRequest(trainerID, courseID);
			// get data course
			Course course = courseService.getCourse(courseID);
			if (!trainerService.existTraierRating(trainerID, course.getTraineeID())) {
				trainerService.insertTrainerRating(trainerID, course.getTraineeID());

			}

			// sent data to client
			model.addAttribute("updateMessage",
					isUpdateSuccess ? "Course has successfully addded!" : "Fail to add course!");
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "forward:/trainer/trainer-Manage-TraineeRequest";
	}

	// show profile of student
	@RequestMapping(value = "/trainer/ViewTraineeProfile")
	public String viewTraineeProfile(Model model, @RequestParam(required = false, name = "traineeID") String traineeID,
			@RequestParam(required = false, name = "subjectID", defaultValue = "0") int subjectID,
			@RequestParam(required = false, name = "actionName") String actionName, HttpServletRequest request) {
		try {
			// get data of student
			Trainee trainee = trainerService.viewTraineeProfile(traineeID);
			// sent data to client
			model.addAttribute("trainer", trainerService.getProfile(SecurityUtils.getPrincipal().getUsername()));
			model.addAttribute("traineeProfile", trainee);
			model.addAttribute("subjectID", subjectID);
			model.addAttribute("actionName", actionName);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "trainer/trainer_ViewStudentProfile";
	}

	// View Profile
	@RequestMapping(value = "/trainer/viewProfile", method = RequestMethod.GET)
	public String trainerViewProfileGET(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = SecurityUtils.getPrincipal().getUsername();
			// get list teacher and address
			List<Trainer> list = trainerService.getListEmail1(email);
			if (list.size() == 2) {
				for (Trainer trainer : list) {
					if (trainer.getEmail().contains("-watting-update-")) {
						trainer = trainerService.getProfile(trainer.getEmail());
						trainer.setEmail(email);
						model.addAttribute("trainerProfile", trainer);

						model.addAttribute("watting", true);
						// get address
						String codeAddress = trainer.getCodeAddress();
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

					}
				}
			} else {
				Trainer trainer = trainerService.getProfile(email);
				model.addAttribute("watting", false);
				model.addAttribute("trainerProfile", trainer);

				String codeAddress = trainer.getCodeAddress();
				String[] arrayCode = codeAddress.split(",");
				String codeVillage = arrayCode[0].trim();
				String codeWard = arrayCode[1].trim();
				String codeDistrict = arrayCode[2].trim();
				String codeProvince = arrayCode[3].trim();

				model.addAttribute("listProvince", traineeService.getListProvince());
				model.addAttribute("listDistrict", traineeService.getListDistrictByProvinceId(codeProvince));
				model.addAttribute("listWard", traineeService.getListWardByDistrictId(codeDistrict));
				model.addAttribute("listVillage", traineeService.getListVillageByWardId(codeWard));

				model.addAttribute("codeProvince", codeProvince);
				model.addAttribute("codeDistrict", codeDistrict);
				model.addAttribute("codeWard", codeWard);
				model.addAttribute("codeVillage", codeVillage);
			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			System.out.println(e.getMessage());
			return "guest/404page";
		}

		return "trainer/trainer_viewProfile";
	}

	// view profile of teacher
	@RequestMapping(value = "/trainer/viewProfile", method = RequestMethod.POST)
	public String trainerViewProfilePOST(@ModelAttribute("trainerProfile") Trainer trainer, Model model, ModelMap mm,
			@RequestParam String city, @RequestParam String district, @RequestParam String wards,
			@RequestParam String numberAddress, @RequestParam String addressHidden, @RequestParam String seDistrict,
			@RequestParam String seProvince, @RequestParam String seWard, @RequestParam String seVillage,
			HttpServletRequest request

	) {
		try {
			String codeAddress = seVillage + "," + seWard + "," + seDistrict + "," + seProvince;
			String emailencryptPass = null;
			String tempEmail = SecurityUtils.getPrincipal().getUsername();

			// set location
			String address = "";

			if (numberAddress.equals("null") || wards.equals("null") || district.equals("null")
					|| city.equals("null")) {
				address = addressHidden;
			} else {
				address = numberAddress + ", " + wards + ", " + district + ", " + city + ", VietNam";
			}

			trainer.setCodeAddress(codeAddress);
			trainer.setAddress(address);
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
			}

			String[] coor = coordinate.split(",");
			double longitude = Double.parseDouble(coor[0].replace("[", ""));
			double latitude = Double.parseDouble(coor[1].replace("]", ""));

			trainer.setLongitude(longitude);
			trainer.setLatitude(latitude);
			// temp account
			try {
				@SuppressWarnings("static-access")
				String encryptPass = sha.encryptPass(tempEmail);
				emailencryptPass = encryptPass + "-watting-update-" + tempEmail;
				Users user = new Users();
				user.setEnabled(-1);
				user.setUsername(emailencryptPass);

				// insert account

				userAuthenticateService.updateUser(user);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("that bai");
			}

			// Update Avatar
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			MultipartFile mul = trainer.getProfileImage();

			String nameAvatarTemp = bCryptPasswordEncoder.encode(mul.getOriginalFilename());
			String nameAvatar = nameAvatarTemp.replace("/", "");
			System.out.println(nameAvatar);
			String avatar = "/trainerAssets/img/" + nameAvatar + mul.getOriginalFilename();
			// set Old avartar

			// set NewAvartar
			if (!mul.isEmpty()) {
				String fileName = servletContext.getRealPath("/") + "WEB-INF\\trainerAssets\\img\\" + nameAvatar
						+ mul.getOriginalFilename();

				System.out.println("ok   " + fileName);

				trainer.setAvatar(avatar);
				try {

					mul.transferTo(new File(fileName));
					System.out.println("thanh cong");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("that bai");
				}
			}

			// temp Update

			trainer.setEmail(emailencryptPass);
			trainer.setVerify(1);
			trainer = trainerService.updateProfile(trainer);
			trainer.setEmail(tempEmail);
//		model.addAttribute("watting",true);
//		model.addAttribute("trainerProfile", trainer);
//		
//		model.addAttribute("listProvince", traineeService.getListProvince());
//		model.addAttribute("listDistrict", traineeService.getListDistrict());
//		model.addAttribute("listWard", traineeService.getListWard());
//		model.addAttribute("listVillage", traineeService.getListVillage());
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "redirect:/trainer/viewProfile";
	}

	@RequestMapping(value = "/trainer/GoBackToTrainerPageAction")
	public String goBackToTrainerPageAction(Model model,
			@RequestParam(required = false, name = "actionName") String actionName) {
		return "forward:/trainer/" + actionName;
	}

	// show and handle course be ended by teacher
	@RequestMapping(value = "/trainer/trainer-Manage-EndedCourse")
	public String trainerEndedCourse(Model model,
			@RequestParam(required = false, name = "subjectID", defaultValue = "0") int subjectID,
			@RequestParam(required = false, name = "curPage", defaultValue = "1") int curPage,
			HttpServletRequest request) {
		try {
			String email = SecurityUtils.getPrincipal().getUsername();
			// get list subject
			model.addAttribute("trainerSubject", trainerService.getSubjectList(email));
			List<CustomCourse> list = trainerService.getTrainerEndedCourse(email, subjectID, curPage);
			// paging
			int totalPage = trainerService.countTrainerEndedCourse(email, subjectID);
			// sent data to client
			model.addAttribute("endedCourseList", list);
			model.addAttribute("subjectID", subjectID);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("curPage", curPage);
			model.addAttribute("trainer", trainerService.getProfile(email));
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "trainer/trainer_manageCourse_endedCourse";
	}

}
