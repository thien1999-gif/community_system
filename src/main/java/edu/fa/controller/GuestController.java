package edu.fa.controller;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.fa.config.JsonReader;
import edu.fa.config.MailValidCode;
import edu.fa.constant.Constant;
import edu.fa.dto.District;
import edu.fa.dto.FileDTO;
import edu.fa.dto.TrainerRating;
import edu.fa.dto.Village;
import edu.fa.dto.Ward;
import edu.fa.model.Authorities;
import edu.fa.model.Subject;
import edu.fa.model.Trainee;
import edu.fa.model.Trainer;
import edu.fa.model.Users;
import edu.fa.service.AccountService;
import edu.fa.service.AuthorService;
import edu.fa.service.SubjectService;
import edu.fa.service.TraineeService;
import edu.fa.service.TrainerService;

//controller for guest =======================================
@Controller
public class GuestController {

	@Autowired
	private TraineeService traineeServive;

	@Autowired
	private TrainerService trainerServive;

	@Autowired
	private AccountService accountService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/guest/phantram")
	@ResponseBody
	public String getPhanTram() {

		System.out.println(Constant.PHANTRAM);
		return Constant.PHANTRAM;
	}

	@RequestMapping(value = "/guest/phanTramTeacher")
	@ResponseBody
	public String getPhanTramTeacher() {

		System.out.println(Constant.PHANTRAMTEACHER);
		return Constant.PHANTRAMTEACHER;
	}
	// address

	// tim quan huyen bang thanh pho
	@RequestMapping(value = "/guest/province")
	@ResponseBody
	public String getDistrict(@RequestParam String provinceId, HttpServletRequest request) {
		String optionDistrict = "<option disabled selected value  >- District -</option>";
		try {
			System.out.println(provinceId);

			List<District> listDistrict = traineeServive.getListDistrictByProvinceId(provinceId);

			for (District district : listDistrict) {

				optionDistrict += "<option value='" + district.getDistrictid() + "'> " + district.getName()
						+ "</option>";
			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return optionDistrict;
	}
	// tim xa/phuong bang district

	@RequestMapping(value = "/guest/district")
	@ResponseBody
	public String getWard(@RequestParam String districtid, HttpServletRequest request) {
		System.out.println(districtid);
		String optionWard = "<option disabled selected value  >- Ward -</option>";
		try {
			List<Ward> listWard = traineeServive.getListWardByDistrictId(districtid);

			for (Ward ward : listWard) {

				optionWard += "<option value='" + ward.getWardid() + "'> " + ward.getName() + "</option>";
			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return optionWard;
	}

	@RequestMapping(value = "/guest/ward")
	@ResponseBody
	public String getVillage(@RequestParam String wardid, HttpServletRequest request) {
		System.out.println(wardid);
		String optionVillage = "<option disabled selected value  >- Village-</option>";
		try {
			List<Village> listVillage = traineeServive.getListVillageByWardId(wardid);

			for (Village village : listVillage) {

				optionVillage += "<option value='" + village.getVillageid() + "'> " + village.getName() + "</option>";
			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return optionVillage;
	}

// display form login==========================================
	@GetMapping(value = "/guest/formLogin1")
	public String displayLogin(Model model, @RequestParam(required = false, name = "DELETE_STATUS") int deleteStatus) {
		model.addAttribute("deleteStatus", deleteStatus);
		return "guest/FormLogin";
	}

	@RequestMapping(value = "/guest/formLogin")
	public String displayLogin(Model model) {

		return "guest/FormLogin";
	}

//display home page ==================================================
	@RequestMapping(value = "/guest/home")
	public String welcomeGuest(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<TrainerRating> topTrainer = trainerServive.getTopTrainer();
			request.setAttribute("topTrainer", topTrainer);
			System.out.println(topTrainer.size());
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "guest/home";
	}

	// logout function
	@RequestMapping(value = "/guest/logout")
	public String logoutProccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, authentication);
			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "redirect:/guest/home";
	}

	// show form login
	@RequestMapping(value = "/guest/showFormRegisterStudent")
	public String showFormRegisterStudent(Model model, HttpServletRequest request) {
		try {
			FileDTO fileDTO = new FileDTO();
			model.addAttribute("file", fileDTO);

			model.addAttribute("listProvince", traineeServive.getListProvince());
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "guest/registerTrainee";
	}

	// handling register student'account
	@PostMapping(value = "/guest/saveStudent")
	public String saveStudent(@ModelAttribute("file") FileDTO fileDTO, HttpServletRequest request, Model model,
			@RequestParam String city, @RequestParam String district, @RequestParam String wards,
			@RequestParam String numberAddress,

			@RequestParam String seDistrict, @RequestParam String seProvince, @RequestParam String seWard,
			@RequestParam String seVillage) {
		// get value from client
		try {
			String codeAddress = seVillage + "," + seWard + "," + seDistrict + "," + seProvince;
			String email = request.getParameter("email");
			String stringPassword = request.getParameter("password");
			String address;
			String faceBook = request.getParameter("faceBook");
			String fullName = request.getParameter("fullName");
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String zalo = request.getParameter("zalo");
			String dayOfBirth = request.getParameter("dayOfBirth");

			MultipartFile multipartFile = fileDTO.getProfileImage();
			String checkImage = multipartFile.getOriginalFilename();
			// end get value from client

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date date = dateFormat.parse(dayOfBirth);

			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			Trainee trainee = new Trainee();
			Users user = new Users();
			Authorities author = new Authorities();
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String fileName = null;
			String password = null;

			Constant.PHANTRAM = "20%";
			// check email trung
			System.out.println("check mail");
			if ((traineeServive.checkEmai(email)) || (trainerServive.checkEmai(email))
					|| (authorService.checkEmai(email)) || (accountService.checkEmai(email))) {
				String error = "Email is exited";
				model.addAttribute("error", error);

				FileDTO fileDTOCheck = new FileDTO();
				model.addAttribute("file", fileDTOCheck);

				model.addAttribute("listProvince", traineeServive.getListProvince());

				return "guest/registerTrainee";
			} // end

			// check day of birth
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			LocalDate l1 = LocalDate.of(year, month, day);
			LocalDate now1 = LocalDate.now();
			Period diff1 = Period.between(l1, now1);

			if (diff1.getYears() <= 6) {
				String error = "You must be 6 years old or more";
				model.addAttribute("errorDate", error);

				FileDTO fileDTOCheck = new FileDTO();
				model.addAttribute("file", fileDTOCheck);

				model.addAttribute("listProvince", traineeServive.getListProvince());
				model.addAttribute("listDistrict", traineeServive.getListDistrict());
				model.addAttribute("listWard", traineeServive.getListWard());
				model.addAttribute("listVillage", traineeServive.getListVillage());
				return "guest/registerTrainee";
			} // end

			Constant.PHANTRAM = "22%";
			// check image
			if (!checkImage.contains(".jpg") && !checkImage.contains(".png")) {
				String error = "Not correct format image ( .jpd or .png )";
				model.addAttribute("errorImage", error);

				FileDTO fileDTOCheck = new FileDTO();
				model.addAttribute("file", fileDTOCheck);

				model.addAttribute("listProvince", traineeServive.getListProvince());
				model.addAttribute("listDistrict", traineeServive.getListDistrict());
				model.addAttribute("listWard", traineeServive.getListWard());
				model.addAttribute("listVillage", traineeServive.getListVillage());
				return "guest/registerTrainee";
			}

			// lay data
			trainee.setEmail(email);
			trainee.setCodeAddress(codeAddress);
			trainee.setDayOfBirth(sqlDate);
			System.out.println("ngay");
			trainee.setFacebook(faceBook);
			trainee.setFullName(fullName);
			trainee.setGender(Integer.parseInt(gender));
			trainee.setPhone(phone);
			trainee.setZalo(zalo);
			Constant.PHANTRAM = "25%";
			// location
			address = numberAddress + ", " + wards + ", " + district + ", " + city + ", VietNam";
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
			}

			String[] coor = coordinate.split(",");
			double longitude = Double.parseDouble(coor[0].replace("[", ""));
			double latitude = Double.parseDouble(coor[1].replace("]", ""));

			trainee.setLongitude(longitude);
			trainee.setLatitude(latitude);

			// lay path file luu vao database
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String nameAvatarTemp = bCryptPasswordEncoder.encode(multipartFile.getOriginalFilename());
			String nameAvatar = nameAvatarTemp.replace("/", "");
			String avatar = "/traineeAssets/img/" + nameAvatar + multipartFile.getOriginalFilename();
			if (multipartFile != null || multipartFile.isEmpty()) {
				fileName = servletContext.getRealPath("/") + "WEB-INF\\traineeAssets\\img\\" + nameAvatar
						+ multipartFile.getOriginalFilename();
				try {
					multipartFile.transferTo(new File(fileName));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // end
			Constant.PHANTRAM = "30%";
			trainee.setAvatar(avatar);

			password = passwordEncoder.encode(stringPassword);// ma hoa password bang bcript

			user.setEnabled(0);
			user.setUsername(email);
			user.setPassword(password);

			author.setUsername(email);
			author.setAuthority("ROLE_TRAINEE");

			accountService.saveAccount(user);

			authorService.saveAuthor(author);

			traineeServive.saveStudent(trainee);

			// send verify code
			// ramdom chuoi verify add vao verify code
			String verifyCode = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
			Constant.PHANTRAM = "80%";
			MailValidCode.sendCodeToUser(verifyCode, email);
			HttpSession session = request.getSession();
			session.setAttribute("verifyCode", verifyCode);
			model.addAttribute("userEmail", email);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "guest/VerifyEmail";
	}

	// verify again when user not active user's account
	@RequestMapping(value = "/guest/verifyAccountAgain")
	public String verifyAccountAgain(@RequestParam String email, Model model, HttpServletRequest request) {
		try {
			String verifyCode = UUID.randomUUID().toString().substring(0, 5).toUpperCase();// random verify code
			MailValidCode.sendCodeToUser(verifyCode, email); // send code via email
			HttpSession session = request.getSession();
			session.setAttribute("verifyCode", verifyCode);
			model.addAttribute("userEmail", email);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "guest/VerifyEmail";
	}

	// register teacher page ==================================================
	// show form teacher login
	@RequestMapping(value = "/guest/showFormRegisterTeacher")
	public String registerTeacher(Model model, HttpServletRequest request) {
		try {
			FileDTO fileDTO = new FileDTO();
			List<Subject> listSubject = new ArrayList<Subject>();
			listSubject = subjectService.selectSubject();
			model.addAttribute("listSubject", listSubject);
			model.addAttribute("file", fileDTO);
			model.addAttribute("listProvince", traineeServive.getListProvince());
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "guest/registerTrainer";
	}

	// sent verify code via user's email
	@PostMapping(value = "/guest/verifyCode")
	public String verifyProcess(Model model, HttpServletRequest request) {

		String url = "guest/VerifyEmail";
		String email = request.getParameter("userEmail");
		String inputCode = request.getParameter("verifyCodeEmail");
		String rightCode = (String) request.getSession().getAttribute("verifyCode");
		try {
			if (inputCode.equals(rightCode)) {
				// set status
				accountService.saveAccountStatus(email, Constant.ACCOUNT_ACTIVE_STATUS);
				url = "guest/FormLogin";
			} else {
				String error = "Incorrect verify code. Please enter again !!!";
				model.addAttribute("errorCode", error);
				model.addAttribute("userEmail", email);
			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return url;
	}

	// handling register teacher'account
	@PostMapping(value = "/guest/saveTeacher")
	public String saveTeacher(@ModelAttribute("file") FileDTO fileDTO, Model model, @RequestParam String city,
			@RequestParam String district, @RequestParam String wards, @RequestParam String numberAddress,
			HttpServletRequest request, @RequestParam String seDistrict, @RequestParam String seProvince,
			@RequestParam String seWard, @RequestParam String seVillage) {
		try {
			// get value from client
			String codeAddress = seVillage + "," + seWard + "," + seDistrict + "," + seProvince;
			String[] subject = request.getParameterValues("subject");
			String email = request.getParameter("email");
			String stringPassword = request.getParameter("password");

			String address;

			String faceBook = request.getParameter("faceBook");
			String fullName = request.getParameter("fullName");
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String zalo = request.getParameter("zalo");
			String dayOfBirth = request.getParameter("dayOfBirth");
			String graduationYear = request.getParameter("graduationYear");
			String salaryPerHour = request.getParameter("salaryPerHour");
			String description = request.getParameter("description");
			String experience = request.getParameter("experience");

			MultipartFile multipartFileImage = fileDTO.getProfileImageTeacher();
			String checkImage = multipartFileImage.getOriginalFilename();

			MultipartFile multipartFileCardID = fileDTO.getProfileCardID();
			String checkImageCardID = multipartFileCardID.getOriginalFilename();

			MultipartFile multipartFileCardIDBehiden = fileDTO.getProfileCardIDBehiden();
			String checkImageCardIDBehiden = multipartFileCardIDBehiden.getOriginalFilename();

			MultipartFile multipartFileCV = fileDTO.getProfileUploadCV();
			String checkCV = multipartFileCV.getOriginalFilename();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Constant.PHANTRAMTEACHER = "30%";
			java.util.Date date = dateFormat.parse(dayOfBirth);

			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			// end

			Trainer trainer = new Trainer();
			Users user = new Users();
			Authorities author = new Authorities();
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String fileNameImage = null;
			String fileNameCard = null;
			String fileNameCV = null;
			String password = null;

			// check email trung
			if ((traineeServive.checkEmai(email)) || (trainerServive.checkEmai(email))
					|| (authorService.checkEmai(email)) || (accountService.checkEmai(email))) {
				String error = "Email is exited";
				model.addAttribute("error", error);

				FileDTO fileDTOCheck = new FileDTO();
				List<Subject> listSubject = new ArrayList<Subject>();
				listSubject = subjectService.selectSubject();
				model.addAttribute("listSubject", listSubject);
				model.addAttribute("file", fileDTOCheck);
				model.addAttribute("listProvince", traineeServive.getListProvince());

				return "guest/registerTrainer";
			} // end

			// check day of birth
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			LocalDate l1 = LocalDate.of(year, month, day);
			LocalDate now1 = LocalDate.now();
			Period diff1 = Period.between(l1, now1);

			if (diff1.getYears() <= 17) {
				String error = "You must be 18 years old or more";
				model.addAttribute("errorDate", error);

				FileDTO fileDTOCheck = new FileDTO();
				List<Subject> listSubject = new ArrayList<Subject>();
				listSubject = subjectService.selectSubject();
				model.addAttribute("listSubject", listSubject);
				model.addAttribute("file", fileDTOCheck);
				model.addAttribute("listProvince", traineeServive.getListProvince());

				return "guest/registerTrainer";
			} // end

			Constant.PHANTRAMTEACHER = "50%";
			// check image
			if (!checkImage.contains(".jpg") && !checkImage.contains(".png")) {
				String error = "Not correct format image ( .jpd or .png )";
				model.addAttribute("errorImage", error);

				FileDTO fileDTOCheck = new FileDTO();
				List<Subject> listSubject = new ArrayList<Subject>();
				listSubject = subjectService.selectSubject();
				model.addAttribute("listSubject", listSubject);
				model.addAttribute("file", fileDTOCheck);
				model.addAttribute("listProvince", traineeServive.getListProvince());
				model.addAttribute("listDistrict", traineeServive.getListDistrict());
				model.addAttribute("listWard", traineeServive.getListWard());
				model.addAttribute("listVillage", traineeServive.getListVillage());
				return "guest/registerTrainer";
			} // end

			// check image card id -front side
			if (!checkImageCardID.contains(".jpg") && !checkImageCardID.contains(".png")) {
				String error = "Not correct format image ( .jpd or .png )";
				model.addAttribute("errorCardID", error);

				FileDTO fileDTOCheck = new FileDTO();
				List<Subject> listSubject = new ArrayList<Subject>();
				listSubject = subjectService.selectSubject();
				model.addAttribute("listSubject", listSubject);
				model.addAttribute("file", fileDTOCheck);
				model.addAttribute("listProvince", traineeServive.getListProvince());
				model.addAttribute("listDistrict", traineeServive.getListDistrict());
				model.addAttribute("listWard", traineeServive.getListWard());
				model.addAttribute("listVillage", traineeServive.getListVillage());
				return "guest/registerTrainer";
			} // end

			// check image card id -back side
			if (!checkImageCardIDBehiden.contains(".jpg") && !checkImageCardIDBehiden.contains(".png")) {
				String error = "Not correct format image ( .jpd or .png )";
				model.addAttribute("errorCardIDBehiden", error);

				FileDTO fileDTOCheck = new FileDTO();
				List<Subject> listSubject = new ArrayList<Subject>();
				listSubject = subjectService.selectSubject();
				model.addAttribute("listSubject", listSubject);
				model.addAttribute("file", fileDTOCheck);
				model.addAttribute("listProvince", traineeServive.getListProvince());
				model.addAttribute("listDistrict", traineeServive.getListDistrict());
				model.addAttribute("listWard", traineeServive.getListWard());
				model.addAttribute("listVillage", traineeServive.getListVillage());
				return "guest/registerTrainer";
			} // end

			// check cv
			if (!checkCV.contains(".pdf")) {
				String error = "Not correct format CV (.pdf )";
				model.addAttribute("errorCV", error);

				FileDTO fileDTOCheck = new FileDTO();
				List<Subject> listSubject = new ArrayList<Subject>();
				listSubject = subjectService.selectSubject();
				model.addAttribute("listSubject", listSubject);
				model.addAttribute("file", fileDTOCheck);
				model.addAttribute("listProvince", traineeServive.getListProvince());

				return "guest/registerTrainer";
			} // end

			// lay data save to database
			trainer.setEmail(email);
			trainer.setCodeAddress(codeAddress);
			trainer.setDayOfBirth(sqlDate);
			trainer.setFacebook(faceBook);
			trainer.setDescription(description);
			trainer.setExperience(experience);
			trainer.setGraduationYear(Integer.parseInt(graduationYear));
			trainer.setFullName(fullName);
			trainer.setGender(Integer.parseInt(gender));
			trainer.setPhone(phone);
			trainer.setZalo(zalo);
			trainer.setSalaryPerHour(Integer.parseInt(salaryPerHour));
			Constant.PHANTRAMTEACHER = "60%";
			// location
			address = numberAddress + ", " + wards + ", " + district + ", " + city + ", VietNam";
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

			// lay path file image luu vao database
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String nameAvatarTemp = bCryptPasswordEncoder.encode(multipartFileImage.getOriginalFilename());
			String nameAvatar = nameAvatarTemp.replace("/", "");
			String avatar = "/trainerAssets/img/" + nameAvatar + multipartFileImage.getOriginalFilename();
			if (multipartFileImage != null || multipartFileImage.isEmpty()) {
				fileNameImage = servletContext.getRealPath("/") + "WEB-INF\\trainerAssets\\img\\" + nameAvatar
						+ multipartFileImage.getOriginalFilename();
				try {
					multipartFileImage.transferTo(new File(fileNameImage));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // end
			trainer.setAvatar(avatar);
			Constant.PHANTRAMTEACHER = "70%";
			// lay path file card ID luu vao database

			String nameCardTemp = bCryptPasswordEncoder.encode(multipartFileCardID.getOriginalFilename());
			String nameCard = nameCardTemp.replace("/", "");
			String cardID = "/trainerAssets/img/" + nameCard + multipartFileCardID.getOriginalFilename();
			if (multipartFileCardID != null || multipartFileCardID.isEmpty()) {
				fileNameCard = servletContext.getRealPath("/") + "WEB-INF\\trainerAssets\\img\\" + nameCard
						+ multipartFileCardID.getOriginalFilename();
				try {
					multipartFileCardID.transferTo(new File(fileNameCard));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // end

			String nameCardTempBehiden = bCryptPasswordEncoder.encode(multipartFileCardIDBehiden.getOriginalFilename());
			String nameCardBehiden = nameCardTempBehiden.replace("/", "");
			String cardIDBehiden = "/trainerAssets/img/" + nameCardBehiden
					+ multipartFileCardIDBehiden.getOriginalFilename();
			if (multipartFileCardIDBehiden != null || multipartFileCardIDBehiden.isEmpty()) {
				String fileNameCardBehiden = servletContext.getRealPath("/") + "WEB-INF\\trainerAssets\\img\\"
						+ nameCardBehiden + multipartFileCardIDBehiden.getOriginalFilename();
				try {
					multipartFileCardIDBehiden.transferTo(new File(fileNameCardBehiden));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // end
			trainer.setIdCard(cardID + "99-card-ID-99" + cardIDBehiden);// tiep

			// lay path file luu vao database
			String nameCVTemp = bCryptPasswordEncoder.encode(multipartFileCV.getOriginalFilename());
			String nameCV = nameCVTemp.replace("/", "");
			String cv = "/trainerAssets/cv/" + nameCV + multipartFileCV.getOriginalFilename();
			if (multipartFileCV != null || multipartFileCV.isEmpty()) {
				fileNameCV = servletContext.getRealPath("/") + "WEB-INF\\trainerAssets\\cv\\" + nameCV
						+ multipartFileCV.getOriginalFilename();
				try {
					multipartFileCV.transferTo(new File(fileNameCV));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // end

			trainer.setResumeLink(cv);

			password = passwordEncoder.encode(stringPassword);// ma hoa password bang bscript

			user.setEnabled(0);
			user.setUsername(email);
			user.setPassword(password);

			author.setUsername(email);
			author.setAuthority("ROLE_TRAINER");

			accountService.saveAccount(user);

			authorService.saveAuthor(author);

			trainerServive.saveTeacher(trainer);

			List<String> list = Arrays.asList(subject);
			for (String subjectID : list) {
				trainerServive.insertTrainerSubject(email, Integer.parseInt(subjectID));
			}
			Constant.PHANTRAMTEACHER = "80%";

			// send verify code via email of user
			String verifyCode = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
			MailValidCode.sendCodeToUser(verifyCode, email);
			HttpSession session = request.getSession();
			session.setAttribute("verifyCode", verifyCode);
			// end
			model.addAttribute("userEmail", email);
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "guest/VerifyEmail";
	}

	// forgot password page ==================================================
	@RequestMapping(value = "/guest/forgot-password")
	public String displayForgotPassword(Model model) {
		return "guest/forgotPassword";
	}

	// Process form submission from forgotPassword page
	@RequestMapping(value = "/guest/forgot", method = RequestMethod.POST)
	public String processForgotPasswordForm(Model model, ModelAndView modelAndView,
			@RequestParam("email") String userEmail, HttpServletRequest request) {
		// Lookup user in database by e-mail
		try {
			@SuppressWarnings("unchecked")
			Optional<Users> optional = accountService.findUserByEmail(userEmail);

			if (!optional.isPresent()) {
				String error = "We didn't find an account for that e-mail address.";
				model.addAttribute("errorMessage", error);

			} else {

				// Generate random 36-character string token for reset password
				Users user = optional.get();
				user.setResetToken(UUID.randomUUID().toString());

				// Save token to database
				accountService.save(user);

				// Email message

				String appUrl = request.getScheme() + "://" + request.getServerName() + ":8087"
						+ request.getContextPath() + "/guest/reset?token=" + user.getResetToken();
				MailValidCode.sendLinkForgotPasswordToUser(appUrl, user.getUsername());

				// Add success message to view
				String sucessMessage = "A password reset link has been sent to " + userEmail;
				model.addAttribute("successMessage", sucessMessage);
				System.out.println("tim thay email");
			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return "guest/forgotPassword";
	}

	// Display form to reset password
	@RequestMapping(value = "/guest/reset", method = RequestMethod.GET)
	public String displayResetPasswordPage(Model model, ModelAndView modelAndView, @RequestParam("token") String token,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			Optional<Users> user = accountService.findUserByResetToken(token);

			if (user.isPresent()) { // Token found in DB
				model.addAttribute("resetToken", token);
			} else { // Token not found in DB
				String error = "Oops!  This is an invalid password reset link.";
				model.addAttribute("errorMessage", error);
			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}

		return "guest/resetPassword";
	}

	// Process reset password form
	@RequestMapping(value = "/guest/processReset", method = RequestMethod.POST)
	public String setNewPassword(Model model, ModelAndView modelAndView,
			@RequestParam Map<String, String> requestParams, RedirectAttributes redir, HttpServletRequest request) {

		String url = "guest/resetPassword";
		String stringPassword = requestParams.get("password");
		try {
			// Find the user associated with the reset token
			@SuppressWarnings("unchecked")
			Optional<Users> user = accountService.findUserByResetToken(requestParams.get("token"));

			// This should always be non-null but we check just in case

			if (user.isPresent()) {

				Users resetUser = user.get();

				// Set new password
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String newPassword = passwordEncoder.encode(stringPassword);// ma hoa password bang bcript
				resetUser.setPassword(newPassword);

				// Set the reset token to null so it cannot be used again
				resetUser.setResetToken(null);

				// Save user
				accountService.save(resetUser);

				// In order to set a model attribute on a redirect, we must use
				// RedirectAttributes
				url = "redirect:/guest/formLogin";
				// return "redirect:/guest/formLogin";

			} else {
				String errorLink = "Oops!  This is an invalid password reset link.";
				model.addAttribute("errorMessage", errorLink);
				url = "guest/resetPassword";
			}
		} catch (Exception e) {
			String note = e.getMessage();
			request.setAttribute("note", note);
			return "guest/404page";
		}
		return url;
	}

	// Going to reset page without a token redirects to login page
//		@ExceptionHandler(MissingServletRequestParameterException.class)
//		public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
//			return new ModelAndView("redirect:login");
//		}
}
