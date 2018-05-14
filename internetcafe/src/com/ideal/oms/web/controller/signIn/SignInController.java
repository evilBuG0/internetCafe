package com.ideal.oms.web.controller.signIn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.ideal.oms.entity.Classs;
import com.ideal.oms.entity.ComputerRoom;
import com.ideal.oms.entity.Grade;
import com.ideal.oms.entity.Lessons;
import com.ideal.oms.entity.SignIn;
import com.ideal.oms.entity.SignInDetail;
import com.ideal.oms.entity.SignInTemp;
import com.ideal.oms.entity.Student;
import com.ideal.oms.framework.web.json.JsonObject;
import com.ideal.oms.security.service.SecurityServiceImp;
import com.ideal.oms.service.ClasssService;
import com.ideal.oms.service.ComputerRoomService;
import com.ideal.oms.service.GradeService;
import com.ideal.oms.service.LessonsService;
import com.ideal.oms.service.SignInDetailService;
import com.ideal.oms.service.SignInService;
import com.ideal.oms.service.SignInTempService;
import com.ideal.oms.service.StudentService;

/**
 * Created by dell on 15-3-2.
 */
@Controller
@RequestMapping(AbstractSignInController.PORTAL_PREFIX)
public class SignInController extends AbstractSignInController{
    private final static Logger logger = LoggerFactory.getLogger(SignInController.class);
    @Resource
    private SecurityServiceImp securityServiceImp;
    @Resource
    private ComputerRoomService computerRoomService;
    @Resource
    private ClasssService classsService;
    @Resource 
    private GradeService gradeService;
    @Resource
    private StudentService studentService;
    @Resource
    private LessonsService lessonsService;
    @Resource
    private SignInTempService signInTempService;
    @Resource
    private SignInService signInService;
    @Resource
    private SignInDetailService signInDetailService;
    

    @RequestMapping("signIn_accept")
    public void signInAccept(HttpServletRequest request, Model model)
			throws Exception{
    	List<ComputerRoom> cprList=computerRoomService.findAll();
    	model.addAttribute("cprList", cprList);
    	List<Classs> classsList=classsService.findClasssByDelFlag();
    	model.addAttribute("classsList", classsList);
    	List<Grade> gradeList=gradeService.findGradeByDelFlag();
    	model.addAttribute("gradeList",gradeList);
    	List<Lessons> lessonsList=lessonsService.findClasssByDelFlag();
    	model.addAttribute("lessonsList",lessonsList);
    	Long num=System.currentTimeMillis();
    	model.addAttribute("num",num.toString());
       
    }
    
    @RequestMapping("get_classs_by_grade")
    @ResponseBody
    public String getClasssByGrade(Model model,
			HttpServletRequest request,
			@RequestParam(required = false) Long gradeId)throws Exception{
    	List<Classs> classsList=classsService.findClasssByGrade(gradeId);
    	com.alibaba.fastjson.JSONObject josn = new com.alibaba.fastjson.JSONObject();
    	return josn.toJSONString(classsList);
    	
    }
    
    @RequestMapping("get_actualNumber_by_classs")
    @ResponseBody
    public String getActualNumberByClasss(Model model,
			HttpServletRequest request,
			@RequestParam(required = false) Long classsId)throws Exception{
    	List<Student> studentList= studentService.getActualNumberByClasss(classsId);
    	com.alibaba.fastjson.JSONObject josn = new com.alibaba.fastjson.JSONObject();
    	return josn.toJSONString(studentList.size());
    	
    }
    
    @RequestMapping("add_absence_pop")
    public void addAbsence(Model model,HttpServletRequest request,
			@RequestParam(required = false) Long classsId,String serialNum)throws Exception {
    	List<Student> studentList= studentService.getActualNumberByClasss(classsId);
    	model.addAttribute("studentList",studentList);
    	model.addAttribute("serialNum",serialNum);
    	
    }
    
    @RequestMapping("save_signIn_temp")
    @ResponseBody
    public String saveTemp(Model model,
	HttpServletRequest request,String serialNum,Long stuId,String explain,String signState)throws Exception{
    	System.out.println(serialNum+",---"+stuId+"----");
    	SignInTemp sit=new SignInTemp();
    	sit.setSerialNum(serialNum);
    	sit.setStudent(studentService.findStudentById(stuId));
    	sit.setSignState(signState);
    	sit.setCreateUser(securityServiceImp.getLoginUser());
    	sit.setExplainInfo(explain);
    	sit.setDelFlag(0);
    	SignInTemp sitT=signInTempService.saveSignInTemp(sit);
    	System.out.println(sitT.getStudent().getStudentName());
    	com.alibaba.fastjson.JSONObject josn = new com.alibaba.fastjson.JSONObject();
    	return josn.toJSONString(sitT);
	}
    
    @RequestMapping("save_signIn")
    @ResponseBody
    public JsonObject saveSignIn(Model model,HttpServletRequest request,
    		String serialNum,
    		Long computerRoom,
    		Long grade,
    		Long classs,
    		Long lessons,
    		Integer arrivalsNumber,
    		Integer actualNumber,
    		String remake)throws Exception{
    	//主表信息
    	SignIn si=new SignIn();
    	si.setComputerRoom(new ComputerRoom(computerRoom));
    	si.setClasssId(new Classs(classs));
    	si.setLessonsId(new Lessons(lessons));
    	si.setActualNumber(actualNumber);
    	si.setArrivalsNumber(arrivalsNumber);
    	si.setCreateDate(new Date());
    	si.setCreateUser(securityServiceImp.getLoginUser());
    	si.setUpdateDate(new Date());
    	si.setUpdateUser(securityServiceImp.getLoginUser());
    	si.setRemark(remake);
    	si.setSignInState("全勤");
    	si.setDelFlag(0);
    	SignIn siNew=signInService.saveSignIn(si);
    	//从表信息
    	List<SignInTemp> sitList=signInTempService.findSignInTempBySerialNumAndUserId(serialNum, securityServiceImp.getLoginUser().getId());
    	List<SignInDetail> sidList=new ArrayList<SignInDetail>();
    	for(SignInTemp sit:sitList){
    		SignInDetail sid=new SignInDetail();
    		sid.setSignId(siNew);
    		sid.setStudentId(sit.getStudent());
    		sid.setSignState(sit.getSignState());
    		sid.setCreateDate(new Date());
    		sid.setCreateUser(securityServiceImp.getLoginUser());
    		sid.setUpdateDate(new Date());
    		sid.setUpdateUser(securityServiceImp.getLoginUser());
    		sid.setExplainInfo(sit.getExplainInfo());
    		sid.setDelFlag(0);
    		sidList.add(sid);
    	}
    	Iterable<SignInDetail> saveSid=signInDetailService.saveSignInDetail(sidList);
    	if(saveSid!=null){
    		signInTempService.deletSignInTempBySerialNumAndUserId(sitList);
    		siNew.setSignInState("缺勤");
    		signInService.saveSignIn(siNew);
    	}
    	return JsonObject.success();
	}
    
    @RequestMapping("signIn_manage")
    public void signInManage(HttpServletRequest request, Model model)
			throws Exception{
    	 Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request, "Q_");
         Pageable pageable = buildPageRequest(request, 15, new Sort(Sort.Direction.DESC, "id"));
         Page<SignIn> page = signInService.searchSignIn(searchParams, pageable);
         model.addAttribute("page", page);
    }
    @RequestMapping("sign_in_detail_pop")
	public void workorderdetailpop(Model model,
			@RequestParam(value = "id", required = false) Long id) {
		
		List<SignInDetail> signInDetailList = signInDetailService.findSignInDetailBySignId(id);
		model.addAttribute("signInDetailList", signInDetailList);
	}

    
}
