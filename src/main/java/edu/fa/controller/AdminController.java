package edu.fa.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.fa.config.MailValidCode;
import edu.fa.dto.AdminProfile;
import edu.fa.model.Admin;
import edu.fa.model.Course;
import edu.fa.model.Subject;
import edu.fa.model.Trainee;
import edu.fa.model.Trainer;
import edu.fa.model.Users;
import edu.fa.service.AdminService;
import edu.fa.service.CourseService;
import edu.fa.service.SubjectService;
import edu.fa.service.TraineeService;
import edu.fa.service.TrainerService;
import edu.fa.service.UserAuthenticateService;
import edu.fa.util.SecurityUtils;

// class controls flow of admin code
@Controller
public class AdminController {

	// declare annotations
	@Autowired
	public SubjectService subSer;
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserAuthenticateService userAuthenticateService;
	@Autowired
	ServletContext servletContext;
	@Autowired
	private TrainerService trainerService;
	@Autowired
	private TraineeService traineeService;
	@Autowired
	private CourseService courseService;

	// display admin dashboard information
	@RequestMapping(value = "/admin/admin-Home")
	public String welcomeGuest(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = SecurityUtils.getPrincipal().getUsername();
			String[] name = email.split("@");
			// show message into client
			model.addAttribute("name", name[0].toUpperCase());
			int countCreate = adminService.countCreatePostRequest();
			int countRegis = adminService.countRegisTrainer();
			int countUpdateInfo = adminService.countUpdateInfoTrainer();
			String notifycation = "You have " + countCreate + " create post request, " + countRegis
					+ " request register of trainer and " + countUpdateInfo + " update info trainer request";
			request.setAttribute("notifycation", notifycation);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			model.addAttribute("note", note);
			return "guest/404page";

		}

		return "/admin/index";
	}

	// display admin information
	@RequestMapping(value = "/admin/AdminInfo", method = RequestMethod.GET)
	public String getProfile(Model model, ModelMap mm, AdminProfile adminProfile, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// process: get personal information of admin
			String email = SecurityUtils.getPrincipal().getUsername();
			Admin admin = adminService.getProfile(email);
			adminProfile.setEmail(admin.getEmail());
			adminProfile.setFullName(admin.getFullName());
			adminProfile.setDayOfBirth(admin.getDayOfBirth());
			adminProfile.setAvatar(admin.getAvatar());
			// end process
			mm.put("adminProfile", adminProfile);
		} catch (Exception e) {
			String note = e.getMessage();
			model.addAttribute("note", note);
			return "guest/404page";
		}

		return "/admin/AdminInfo";
	}

	// update admin information
	@RequestMapping(value = "/admin/AdminInfo", method = RequestMethod.POST)
	public String postProfile(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, @ModelAttribute("adminProfile") AdminProfile adminProfile,
			Model model, ModelMap mm, Admin admin, Users user, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			admin = adminService.getProfile(adminProfile.getEmail());
			user = userAuthenticateService.getUser(adminProfile.getEmail());
			// convert dto to model
			admin.setEmail(adminProfile.getEmail());
			admin.setFullName(adminProfile.getFullName());
			admin.setDayOfBirth(adminProfile.getDayOfBirth());
			user.setUsername(adminProfile.getEmail());

			// update Avatar
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			MultipartFile mul = adminProfile.getProfileImage();
			String nameAvatarTemp = bCryptPasswordEncoder.encode(mul.getOriginalFilename());
			String nameAvatar = nameAvatarTemp.replace("/", "");
			adminProfile.setAvatar(admin.getAvatar());
			// check link avatar
			String avatar = "/adminAssets/img/avatars/" + nameAvatar + mul.getOriginalFilename();
			if (!mul.isEmpty()) {
				String fileName = servletContext.getRealPath("/") + "WEB-INF\\adminAssets\\img\\avatars\\" + nameAvatar
						+ mul.getOriginalFilename();

				try {
					admin.setAvatar(avatar);
					adminProfile.setAvatar(avatar);
					mul.transferTo(new File(fileName));
					System.out.println("thanh cong");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("that bai");
				}
			} // end update avatar

			// Update Profile
			adminService.updateProfile(admin);
			mm.put("adminProfile", adminProfile);

			// Update Password
			if (!newPassword.isEmpty() && !oldPassword.isEmpty()) {

				if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {

					user.setPassword(bCryptPasswordEncoder.encode(newPassword));
					userAuthenticateService.updateUser(user);
				} else {

					model.addAttribute("errorPassword", "Old Password not invalid");
					return "/admin/AdminInfo";
				}
			}
		} catch (Exception e) {
			String note = e.getMessage();
			model.addAttribute("note", note);
			return "guest/404page";
		}

		return "/admin/AdminInfo";
	}

	// Get the teacher's registered account list
	@RequestMapping(value = "/admin/ListTeacherRequest")
	public String listTeacherRequest(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Trainer> listtrainer = trainerService.getListByStatus();
			model.addAttribute("listTrainer", listtrainer);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			model.addAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/ListTeacherRequest";
	}

	// view information of teacher
	@RequestMapping(value = "/admin/TeacherRegisterRequest", method = RequestMethod.GET)
	public String teacherRegisterRequest(Model model, @RequestParam String id, ModelMap mm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// process: get information of teacher
			Trainer trainer = trainerService.getProfile(id);
			String idCard = trainer.getIdCard();
			String[] idCards = idCard.split("99-card-ID-99");
			model.addAttribute("frontIdCard", idCards[0]);
			model.addAttribute("behindIdCard", idCards[1]);
			mm.put("trainer", trainer);
			// end process

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			model.addAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/TeacherRegisterRequest";
	}

	// get value for update information of teacher
	@RequestMapping(value = "/admin/TeacherRegisterRequest", method = RequestMethod.POST)
	public String teacherRegisterRequestPOST(@ModelAttribute("trainer") Trainer trainer, Model model, ModelMap mm,
			@RequestParam String idCard, @RequestParam String resumeLink, @RequestParam String avatar,
			@RequestParam double longitude, @RequestParam double latitude, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// process: set value for parameter
			trainer.setVerify(1);
			trainer.setIdCard(idCard);
			trainer.setResumeLink(resumeLink);
			trainer.setAvatar(avatar);
			trainer.setLongitude(longitude);
			trainer.setLatitude(latitude);
			// end
			trainerService.updateProfile(trainer);// update information
		} catch (Exception e) {
			String note = e.getMessage();
			model.addAttribute("note", note);
			return "guest/404page";
		}
		return "redirect:/admin/ListTeacherRequest";
	}

	// deny register request of teacher
	@RequestMapping(value = "/admin/DenyRegisterRequest", method = RequestMethod.GET)
	public String DenyRegisterRequest(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Users user = userAuthenticateService.getUser(id);
			user.setEnabled(2);
			userAuthenticateService.updateUser(user);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "redirect:/admin/ListTeacherRequest";
	}

	@RequestMapping(value = "/admin/TeacherInformation")
	public String welcomeGuest4(Model model) {

		Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
		model.addAttribute("profileAdmin", profileAdmin);
		return "/admin/TeacherInformation";
	}

	// admin verify update information of teacher
	@RequestMapping(value = "/admin/ListTeacherRequestUpdate")
	public String listTeacherRequestUpdate(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String keyword = "-watting-update-";
			List<Trainer> listTrainer = trainerService.getListEmail1("-watting-update-");
			for (Trainer trainer : listTrainer) {
				String[] stringEmail = trainer.getEmail().split(keyword);
				trainer.setEmail(stringEmail[1]);
			}

			model.addAttribute("listTrainer", listTrainer);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/ListTeacherRequestUpdate";
	}

	// admin deny updating information of teacher
	@RequestMapping(value = "/admin/DenyUpdateRequest")
	public String denyUpdateRequest(@RequestParam("id") String emailTemp) {
		trainerService.deleteShaProfile(emailTemp);
		return "redirect:/admin/ListTeacherRequestUpdate";
	}

	//
	@RequestMapping(value = "/admin/TeacherUpdateRequest", method = RequestMethod.GET)
	public String teacherUpdateRequest(@RequestParam String id, Model model, ModelMap mm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyword = "-watting-update-";

			List<Trainer> listTrainer = trainerService.getListEmail1(id);

			Trainer trainerOld = new Trainer();
			Trainer trainerNew = new Trainer();

			for (Trainer trainer : listTrainer) {
				if (trainer.getEmail().contains(keyword)) {
					model.addAttribute("keyword", trainer.getEmail());
					trainerNew = trainer;
				} else {
					trainerOld = trainer;
				}
			}

			mm.put("trainer", trainerNew);
			model.addAttribute("idEmail", id);
			model.addAttribute("trainerNew", trainerNew);
			model.addAttribute("trainerOld", trainerOld);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/TeacherUpdateRequest";
	}

	//
	@RequestMapping(value = "/admin/TeacherUpdateRequest", method = RequestMethod.POST)
	public String teacherUpdateRequestPOST(@RequestParam("idEmail") String email,
			@ModelAttribute("trainer") Trainer trainer, Model model, ModelMap mm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String shaEmail = trainer.getEmail();
			trainer.setEmail(email);

			trainerService.updateProfile(trainer);
			trainerService.deleteShaProfile(shaEmail);

			mm.put("trainer", trainer);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "redirect:/admin/ListTeacherRequestUpdate";
	}

	// get list student's account
	@RequestMapping(value = "/admin/ListTrainee")
	public String welcomeGuest7(Model model, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws ServletException, IOException {
		try {
			List<Trainee> theTrainees = traineeService.getListTrainee();
			PagedListHolder<Trainee> pagedListHolder = new PagedListHolder<Trainee>(theTrainees);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setPageSize(10);// set number of line of page
			modelMap.put("pagedListHolder", pagedListHolder);

			// add the customers to the model
			model.addAttribute("listTrainee", theTrainees);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/ListTrainee";
	}

	@RequestMapping(value = "/admin/TraineeInformation")
	public String welcomeGuest8(Model model) {

		Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
		model.addAttribute("profileAdmin", profileAdmin);
		return "/admin/TraineeInformation";
	}

	// get list teacher's account
	@RequestMapping(value = "/admin/ListTrainers")
	public String welcomeGuest9(Model model, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws ServletException, IOException {
		try {
			List<Trainer> theTrainers = trainerService.getListTrainer();
			// process: paging
			PagedListHolder<Trainer> pagedListHolder = new PagedListHolder<Trainer>(theTrainers);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setPageSize(10);// set number of line of page
			modelMap.put("pagedListHolder", pagedListHolder);
			// end process
			request.setAttribute("listTrainer", theTrainers);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/ListTrainers";
	}

	@RequestMapping(value = "/admin/TrainerInformation")
	public String welcomeGuest10(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/TrainerInformation";
	}

	// show information of list course
	@RequestMapping(value = "/admin/ListCourse")
	public String welcomeGuest11(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Subject> list = subSer.showAll();
			model.addAttribute("list", list);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/ListCourse";
	}

	// search course by name's course
	@RequestMapping(value = "/admin/searchCourse")
	public String searchSubject(Model model, @RequestParam(required = false) String searchCourseValue,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			model.addAttribute("searchCourseValue", searchCourseValue);
			List<Subject> list = subSer.searchSubjectNameLike(searchCourseValue);
			model.addAttribute("list", list);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/ListCourse";
	}

	// search teacher by email
	@RequestMapping(value = "/admin/searchTrainerByEmail")
	public String searchTeacherByEmail(Model model, @RequestParam(required = false) String searchName,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		model.addAttribute("searchName", searchName);
		List<Trainer> listTrainer = null;
		try {
			listTrainer = trainerService.getListEmail(searchName);

			request.setAttribute("listTrainer", listTrainer);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
		model.addAttribute("profileAdmin", profileAdmin);
		return "/admin/ListTrainers";
	}

	// search student by email
	@RequestMapping(value = "/admin/searchTraineeByEmail")
	public String searchTraineeByEmail(Model model, @RequestParam(required = false) String searchNameTrainee,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			model.addAttribute("searchNameTrainee", searchNameTrainee);
			List<Trainee> listTrainee = null;

			listTrainee = traineeService.getListEmail(searchNameTrainee);

			request.setAttribute("listTrainee", listTrainee);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/ListTrainee";
	}

	// add new course by admin
	@RequestMapping(value = "/admin/addNewCourse")
	public String addNewSubject(Model model, @RequestParam(required = false) String addCourseValue,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean success = subSer.addNewSubject(addCourseValue);
			if (model.containsAttribute("status")) {
				model.addAttribute("status", null);
			} // reset status is null
			if (success) {
				model.addAttribute("status", "Add Successful!");
			} else {
				model.addAttribute("status", "Add Fail!");
			}
			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "forward:/admin/ListCourse";
	}

	// delete subject by admin
	@RequestMapping(value = "/admin/deleteSubject")
	public String deleteSubject(Model model, @RequestParam int subjectId, HttpServletRequest request) {
		boolean success = false;
		try {
			success = subSer.deteleSubjectByID(subjectId);
			if (model.containsAttribute("status")) {
				model.addAttribute("status", null);
			} // reset status is null
			if (success) {

				model.addAttribute("status", "Delete Successful!");

			} else {

				model.addAttribute("status", "Delete fail!");

			}
			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "forward:/admin/ListCourse";
	}

	// update subject by admin
	@RequestMapping(value = "/admin/updateSubject")
	public String update(Model model, @RequestParam int subjectId, @RequestParam String subjectName,
			HttpServletRequest request) {
		boolean success = false;

		try {
			Subject subject = subSer.findSubject(subjectId);
			subject.setSubject(subjectName);
			success = subSer.updateSubject(subject);

			if (model.containsAttribute("status")) {
				model.addAttribute("status", null);
			} // reset status is null
			if (success) {

				model.addAttribute("status", "Update Successful!");

			} else {

				model.addAttribute("status", "Update fail!");

			}

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			// TODO: handle exception
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "forward:/admin/ListCourse";
	}

	// delete all subject by admin
	@RequestMapping(value = "/admin/deleteAllSubject", method = RequestMethod.POST)
	public String update(Model model, HttpServletRequest request) {
		boolean success = false;
		try {
			String statusSuccess = "";
			String statusFail = "";
			String status = "";
			if (request.getParameterValues("IDsubject") == null) {
				model.addAttribute("status", "Please choose checkbox if you want to delete many subject");
			} else {
				for (String subjectId : request.getParameterValues("IDsubject")) {
					Subject subject = subSer.findSubject(Integer.parseInt(subjectId));
					success = subSer.deteleSubjectByID(Integer.parseInt(subjectId));
					if (!success) {
						statusFail += subject.getSubject() + ", ";
					} else {
						statusSuccess += subject.getSubject() + ", ";
					}

				}
				if (model.containsAttribute("status")) {
					model.addAttribute("status", null);
				} // reset status is null

				if (!statusSuccess.equals("")) {
					status = "Delete successful: " + statusSuccess;
				}
				if (!statusFail.equals("")) {
					status += ", Cannot not delete: " + statusFail;
				}
				model.addAttribute("status", status);
			}

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			// TODO: handle exception
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "forward:/admin/ListCourse";
	}

	// admin verify list post request of student
	@RequestMapping(value = "/admin/ListRequestVerifyPost", method = RequestMethod.GET)
	public String listRequestVerifyPost(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Course> listCourse = courseService.searchCoursesByStatus(0);
			model.addAttribute("listCourse", listCourse);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/ListRequestVerifyPost";
	}

	// view detail post by admin
	@RequestMapping(value = "/admin/CourseDetail", method = RequestMethod.GET)
	public String courseDetail(@RequestParam int id, ModelMap mm, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Course course = courseService.getCourse(id);
			Trainee trainee = traineeService.findTraineeByEmail(course.getTraineeID());
			model.addAttribute("trainee", trainee);
			model.addAttribute("listSubject", subSer.showAll());
			model.addAttribute("listSubjectID", subSer.getListSubjectID());

			mm.put("modelCourse", course);

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/RequestPostDetail";
	}

	// accept post of student by admin
	@RequestMapping(value = "/admin/CourseDetail", method = RequestMethod.POST)
	public String courseDetailPost(@RequestParam("id") int CourseID, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Course course = courseService.getCourse(CourseID);
			course.setStatus(1);
			courseService.createCourse(course);
			Trainee traineeInfomation = traineeService.getInfoTrainee(course.getTraineeID());

			List<Trainer> listTrainer = trainerService.suggestTrainer(traineeInfomation.getLongitude(),
					traineeInfomation.getLatitude(), course.getSubjectID(), course.getDayInWeek(),
					course.getTimeInDay());
			Subject subject = subSer.findSubject(course.getSubjectID());

			if (!listTrainer.isEmpty()) {
				MailValidCode.suggestTrainer(listTrainer, course.getTraineeID());

				for (Trainer trainer : listTrainer) {

					MailValidCode.suggestForTrainer(course, subject.getSubject(), trainer.getEmail());
				}

			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "redirect:/admin/ListRequestVerifyPost";
	}

	// admin deny post of student
	@RequestMapping(value = "/admin/DenyCoursePost")
	public String denyCoursePost(@RequestParam("id") int CourseID) {
		courseService.deleteCourse(CourseID);
		return "redirect:/admin/ListRequestVerifyPost";
	}

	@RequestMapping(value = "/admin/deleteAllPost")
	public String denyCoursePost(HttpServletRequest request, Model model) {

		try {
			if (request.getParameterValues("courseID") == null) {
				model.addAttribute("message", "Please choose checkbox if you want to delete many post");
				return "forward:/admin/ListRequestVerifyPost";
			}
			for (String courseID : request.getParameterValues("courseID")) {

				courseService.deleteCourse(Integer.parseInt(courseID));
			}
		} catch (Exception e) {
			// TODO: handle exception
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "redirect:/admin/ListRequestVerifyPost";
	}

	// admin delete student's account by admin
	@GetMapping("/admin/deleteTraineeByAdmin")
	public String deleteTraineeByAdmin(@RequestParam String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			traineeService.deleteTraineeByAdminSetEnable(id);
			String noti = "Ban account " + id + " successfull!";

			request.setAttribute("notifycation", noti);
			request.getRequestDispatcher("/admin/ListTrainee").forward(request, response);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "";

	}

	// admin delete teacher's account by admin
	@GetMapping("/admin/deleteTrainerByAdmin")
	public String deleteTrainerByAdmin(@RequestParam String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			trainerService.deleteTrainerByAdminSetEnable(id);
			String noti = "Ban account " + id + " successfull!";

			request.setAttribute("notifycation", noti);
			request.getRequestDispatcher("/admin/ListTrainers").forward(request, response);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "";

	}

	// admin view detail information of teacher'account
	@GetMapping("/admin/detailTrainer")
	public String getInfoTrainerByAdmin(Model model, @RequestParam String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Trainer trainer = trainerService.getProfile(id);
			String cardId = trainer.getIdCard();
			String[] partCardId = cardId.split("99-card-ID-99");
			System.out.println(partCardId[1]);
			request.setAttribute("frontCard", partCardId[0]);
			request.setAttribute("behindCard", partCardId[1]);
			request.setAttribute("infoTrainer", trainerService.getProfile(id));
			Users user = userAuthenticateService.getUser(id);
			request.setAttribute("statusBan", user.getEnabled());

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
			request.getRequestDispatcher("/admin/TrainerInformation").forward(request, response);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "";
	}

	// admin view detail information of student'account
	@GetMapping("/admin/detailTrainee")
	public String getInfoTraineeByAdmin(Model model, @RequestParam String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("infoTrainee", traineeService.getInfoTrainee(id));
			Users user = userAuthenticateService.getUser(id);
			request.setAttribute("statusBan", user.getEnabled());

			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
			request.getRequestDispatcher("/admin/TraineeInformation").forward(request, response);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "";
	}

	// list account's teacher and student was lock by admin
	@GetMapping("/admin/BlackList")
	public String BlacList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		try {
			List<Trainer> blackList = trainerService.getBlackListTrainer();
			List<Trainee> blackListTrainee = traineeService.getBlackListTrainer();

			request.setAttribute("blackListTrainer", blackList);
			request.setAttribute("blackListTrainee", blackListTrainee);
			for (int i = 0; i < blackListTrainee.size(); i++) {
				System.out.println(blackListTrainee.get(i));

			}
			Admin profileAdmin = adminService.getProfile(SecurityUtils.getPrincipal().getUsername());
			model.addAttribute("profileAdmin", profileAdmin);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "/admin/BlackList";
	}

	// unlock account's teacher by admin
	@GetMapping("/admin/unBanAccountTrainer")
	public String unBanTrainerByAdmin(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			trainerService.unBanTrainer(id);
			String noti = "Un ban account " + id + " successfull!";

			request.setAttribute("notifycation", noti);
			request.getRequestDispatcher("/admin/BlackList").forward(request, response);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "";

	}

	// unlock account's student by admin
	@GetMapping("/admin/unBanAccountTrainee")
	public String unBanTraineeByAdmin(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			trainerService.unBanTrainer(id);
			String noti = "Un ban account " + id + " successfull!";

			request.setAttribute("notifycation", noti);
			request.getRequestDispatcher("/admin/BlackList").forward(request, response);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "";
	}

}
