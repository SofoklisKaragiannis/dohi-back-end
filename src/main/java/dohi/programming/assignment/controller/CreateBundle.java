package dohi.programming.assignment.controller;

import dohi.programming.assignment.framework.BundleStorage;
import dohi.programming.assignment.framework.V1;
import dohi.programming.assignment.model.Bundle;
import dohi.programming.assignment.model.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(V1.URI_CREATE_ABSOLUTE)
public class CreateBundle {
    @Autowired
    BundleStorage bundleStorage;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeferredResult<ResponseEntity<BasicResponse>> createBundle(@RequestBody final Bundle bundleRequest,
                                                                      HttpServletRequest request) {
        DeferredResult<ResponseEntity<BasicResponse>> deferredResult = new DeferredResult<>();
        BasicResponse basicResponse = new BasicResponse();
        if (bundleRequest.getId() == null){
            basicResponse.setMessage("Invalid bundle id");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST));
        } else if (bundleStorage.getBundleMap().get(bundleRequest.getId()) != null) {
            basicResponse.setId(bundleRequest.getId());
            basicResponse.setMessage("Bundle already exists");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.CONFLICT));
        } else {
            bundleStorage.addBundle(bundleRequest.getId(), bundleRequest);
            basicResponse.setId(bundleRequest.getId());
            basicResponse.setMessage("Bundle created");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.OK));
        }

        return deferredResult;
    }
}
