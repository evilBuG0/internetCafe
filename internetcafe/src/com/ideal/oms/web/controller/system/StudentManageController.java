package com.ideal.oms.web.controller.system;

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
import com.ideal.oms.entity.Grade;
import com.ideal.oms.entity.Student;
import com.ideal.oms.framework.web.json.JsonObject;
import com.ideal.oms.security.service.SecurityServiceImp;
import com.ideal.oms.service.ClasssService;
import com.ideal.oms.service.GradeService;
import com.ideal.oms.service.StudentService;

@Controller
@RequestMapping(AbstractSystemController.PORTAL_PREFIX)
public class StudentManageController extends AbstractSystemController {
    Logger logger = LoggerFactory.getLogger(StudentManageController.class);

    @Resource
    private SecurityServiceImp securityServiceImp;
    
    @Resource
    private StudentService studentService;
    @Resource
    private ClasssService classsService;
    @Resource
    private GradeService gradeService;

    

    @RequestMapping("student_manage")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request, "Q_");
        Pageable pageable = buildPageRequest(request, 15, new Sort(Sort.Direction.DESC, "id"));
        Page<Student> page = studentService.searchStudent(searchParams, pageable);
        model.addAttribute("page", page);
    }

    @RequestMapping("edit_student_pop")
    public void edit(Model model, @RequestParam(value = "id", required = false) Long id) {
        List<Grade> gradeList = gradeService.findGradeByDelFlag();
        model.addAttribute("gradeList", gradeList);
        List<Classs> classsList=classsService.findClasssByDelFlag();
        model.addAttribute("classsList", classsList);
        Student studentInfo = null;
        if (id != null) {
            studentInfo = studentService.findStudentById(id);
        }
        model.addAttribute("studentInfo", studentInfo);
    }
    
    @RequestMapping("save_student")
    @ResponseBody
    public JsonObject save(Model model, @RequestParam(value = "id") Long id,
                           @RequestParam(required = false) String studentName,
                           @RequestParam(required = false) Integer age,
                           @RequestParam(required = false) Integer sex,
                           @RequestParam(required = false) String address,
                           @RequestParam(required = false) Long gradeId,
                           @RequestParam(required = false) Long classsId
                           ) {
        Student student = new Student();
        // 编辑
        if (id != null) {
        	student = studentService.findStudentById(id);
        	student.setStudentName(studentName);
        	student.setAge(age);
        	student.setSex(sex);
        	student.setAddress(address);
        	student.setClasssId(new Classs(classsId));
        	student.setGrade(new Grade(gradeId));
            studentService.saveStudent(student);
            
        }
        // 新增
        else {
        	student.setStudentName(studentName);
        	student.setAge(age);
        	student.setSex(sex);
        	student.setAddress(address);
        	student.setClasssId(new Classs(classsId));
        	student.setGrade(new Grade(gradeId));
            student.setCreateDate(new Date());
            student.setCreateUser(securityServiceImp.getLoginUser());
            student.setDelFlag(0);
            studentService.saveStudent(student);
            
        }
        return JsonObject.success();
    }
   
    @RequestMapping("delete_student")
    @ResponseBody
    public JsonObject delete(@RequestParam(value = "id") Long[] ids) {
        if(ids != null) {
            studentService.deleteStudent(ids);
        }
        return JsonObject.success();
    }
    /*
    @RequestMapping("reset_password_pop")
    public void resetPassword(Model model, @RequestParam(value = "id", required = false) Long id) {
        User userInfo = null;
        if (id != null) {
            userInfo = securityServiceImp.getUser(id);
        }
        model.addAttribute("userInfo", userInfo);
    }

    @RequestMapping("reset_password")
    @ResponseBody
    public JsonObject reset(Model model, @RequestParam(value = "id") Long id,
                            @RequestParam(required = false) String password) {
        User user = securityServiceImp.getUser(id);
        user.setPassword(DigestUtils.md5Hex(password));
        securityServiceImp.saveUser(user);
        return JsonObject.success();
    }*/
}
