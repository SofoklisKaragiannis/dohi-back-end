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
@RequestMapping(V1.URI_UPDATE_ABSOLUTE)
public class UpdateBundle {
    @Autowired
    BundleStorage bundleStorage;

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeferredResult<ResponseEntity<BasicResponse>> updateBundle(@RequestBody final Bundle bundleRequest,
                                                                      HttpServletRequest request) {
        DeferredResult<ResponseEntity<BasicResponse>> deferredResult = new DeferredResult<>();
        BasicResponse basicResponse = new BasicResponse();
        if (bundleRequest.getId() == null){
            basicResponse.setMessage("Invalid bundle id");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST));
        } else if (bundleStorage.getBundleMap().get(bundleRequest.getId()) != null) {
            bundleStorage.getBundleMap().remove(bundleRequest.getId());
            bundleStorage.addBundle(bundleRequest.getId(), bundleRequest);
            basicResponse.setId(bundleRequest.getId());
            basicResponse.setMessage("Bundle updated");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.OK));
        } else {
            bundleStorage.addBundle(bundleRequest.getId(), bundleRequest);
            basicResponse.setId(bundleRequest.getId());
            basicResponse.setMessage("Bundle not exist");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.NOT_FOUND));
        }

        return deferredResult;
    }
}
