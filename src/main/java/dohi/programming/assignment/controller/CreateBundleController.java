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

/**
 * CreateBundleController takes care calls of type
 * Method: POST Url: https://{host}/rest/v1/create body: Bundle in JSON format
 *
 *  Response body of type BasicResponse
 *  JSON format
 *  {
 *      "id": {Integer},
 *      "message": {String}
 *  }
 */
@RestController
@RequestMapping(V1.URI_CREATE_ABSOLUTE)
public class CreateBundleController {
    @Autowired
    BundleStorage bundleStorage;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeferredResult<ResponseEntity<BasicResponse>> createBundle(@RequestBody final Bundle bundleRequest,
                                                                      HttpServletRequest request) {

        DeferredResult<ResponseEntity<BasicResponse>> deferredResult = new DeferredResult<>();
        BasicResponse basicResponse = new BasicResponse();

        //control the validity of requesting bundle id
        if (bundleRequest.getId() == null){
            basicResponse.setMessage("Invalid bundle id");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST));
        //check if the bundle already exists
        } else if (bundleStorage.getBundleMap().get(bundleRequest.getId()) != null) {
            basicResponse.setId(bundleRequest.getId());
            basicResponse.setMessage("Bundle already exists");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.CONFLICT));
        // create bundle if correct data
        } else {
            bundleStorage.addBundle(bundleRequest.getId(), bundleRequest);
            basicResponse.setId(bundleRequest.getId());
            basicResponse.setMessage("Bundle created");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.OK));
        }

        return deferredResult;
    }
}
