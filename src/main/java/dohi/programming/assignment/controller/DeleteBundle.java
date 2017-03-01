package dohi.programming.assignment.controller;

import dohi.programming.assignment.framework.BundleStorage;
import dohi.programming.assignment.framework.V1;
import dohi.programming.assignment.model.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(V1.URI_DELETE_ABSOLUTE)
public class DeleteBundle {

    @Autowired
    BundleStorage bundleStorage;

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeferredResult<ResponseEntity<BasicResponse>> lookup(@RequestParam Map<String, String> parameters, HttpServletRequest request) {
        DeferredResult<ResponseEntity<BasicResponse>> deferredResult = new DeferredResult<>();
        Map<String, String> requestParameters = new HashMap<>(parameters);

        BasicResponse basicResponse = new BasicResponse();
        String bundleId = requestParameters.get("id");
        if (bundleId == null) {
            basicResponse.setMessage("Invalid bundle id");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST));
        } else if (bundleStorage.getBundleMap().get(Integer.parseInt(bundleId)) == null) {
            basicResponse.setMessage("Bundle not exist");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.NOT_FOUND));
        } else {
            bundleStorage.getBundleMap().remove(Integer.parseInt(bundleId));
            basicResponse.setId(Integer.parseInt(bundleId));
            basicResponse.setMessage("Bundle deleted");
            deferredResult.setResult(new ResponseEntity<>(basicResponse, HttpStatus.OK));
        }

        return deferredResult;
    }

}
