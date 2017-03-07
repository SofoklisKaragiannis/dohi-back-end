package dohi.programming.assignment.controller;

import dohi.programming.assignment.framework.BundleStorage;
import dohi.programming.assignment.framework.V1;
import dohi.programming.assignment.model.Bundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * RetrieveBundleController takes care calls of type
 * Method: GET Url: https://{host}/rest/v1/retrieveAll}
 *
 *  Response body of type Bundle in JSON format
 *
 */
@CrossOrigin
@RestController
@RequestMapping(V1.URI_RETRIEVE_ALL_ABSOLUTE)
public class RetrieveAllBundlesController {

    @Autowired
    BundleStorage bundleStorage;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeferredResult<ResponseEntity<List<Bundle>>> retrieveBundles(HttpServletRequest request) {
        DeferredResult<ResponseEntity<List<Bundle>>> deferredResult = new DeferredResult<>();

        List<Bundle> bundleList = bundleStorage.getBundleMap().entrySet().stream()
                .map(x -> x.getValue())
                .collect(Collectors.toList());
        deferredResult.setResult(new ResponseEntity<>(bundleList, HttpStatus.OK));
        return deferredResult;
    }
}
