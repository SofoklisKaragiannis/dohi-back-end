package dohi.programming.assignment.controller;

import dohi.programming.assignment.framework.BundleStorage;
import dohi.programming.assignment.framework.V1;
import dohi.programming.assignment.model.Bundle;
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
@RequestMapping(V1.URI_RETRIEVE_ABSOLUTE)
public class RetrieveBundle {

    @Autowired
    BundleStorage bundleStorage;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeferredResult<ResponseEntity<Bundle>> lookup(@RequestParam Map<String, String> parameters, HttpServletRequest request) {
        DeferredResult<ResponseEntity<Bundle>> deferredResult = new DeferredResult<>();
        Map<String, String> requestParameters = new HashMap<>(parameters);

        Bundle bundleResponse = new Bundle();
        String bundleId = requestParameters.get("id");

        if (bundleId == null) {
            deferredResult.setResult(new ResponseEntity<>(bundleResponse, HttpStatus.BAD_REQUEST));
        } else if (bundleStorage.getBundleMap().get(Integer.parseInt(bundleId)) == null) {
            deferredResult.setResult(new ResponseEntity<>(bundleResponse, HttpStatus.NOT_FOUND));
        } else {
            deferredResult.setResult(new ResponseEntity<>(bundleStorage.getBundleMap().get(Integer.parseInt(bundleId)), HttpStatus.OK));
        }

        return deferredResult;
    }
}
