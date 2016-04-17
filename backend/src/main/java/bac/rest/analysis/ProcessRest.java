package bac.rest.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ProcessRest {


    @JsonProperty("processModel")
    private List<ProcessModelRest> processModel;

    @JsonProperty("mostFrequentlyProcess")
    private Map<String, Map<String, Integer>> mostFrequentlyProcess;

    public List<ProcessModelRest> getProcessModel() {
        return processModel;
    }

    public void setProcessModel(List<ProcessModelRest> processModel) {
        this.processModel = processModel;
    }

    public Map<String, Map<String, Integer>> getMostFrequentlyProcess() {
        return mostFrequentlyProcess;
    }

    public void setMostFrequentlyProcess(Map<String, Map<String, Integer>> mostFrequentlyProcess) {
        this.mostFrequentlyProcess = mostFrequentlyProcess;
    }
}

