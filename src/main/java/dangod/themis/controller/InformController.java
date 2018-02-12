package dangod.themis.controller;

import dangod.themis.core.result.Result;
import dangod.themis.model.po.Inform;
import dangod.themis.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.constant.Message.INFORM_NOT_EXISTS_MESSAGE;
import static dangod.themis.controller.constant.Message.INFORM_SUCCESS_MESSAGE;
import static dangod.themis.controller.constant.Status.NOT_FIND;
import static dangod.themis.controller.constant.Status.SUCCESS;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping(value = "/inform")
public class InformController {
    @Autowired
    private InformService informService;

    @RequestMapping(value = "/id/{id}", method = GET)
    public String getInformById(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable("id")Long id){
        Inform inform = informService.getById(id);
        if(inform == null) return Result.send(NOT_FIND, null, INFORM_NOT_EXISTS_MESSAGE);
        return Result.send(SUCCESS, inform, INFORM_SUCCESS_MESSAGE);
    }
}
