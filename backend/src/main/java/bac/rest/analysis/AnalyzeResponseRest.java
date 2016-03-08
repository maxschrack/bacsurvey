package bac.rest.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeResponseRest {

    @JsonProperty("visits_startPage")
    private Long startPageVisits;

    @JsonProperty("avgVisitTime_startPage")
    private int avgVisitTime_startPage;

    @JsonProperty("visits_pages")
    private List<Long> pageVisits;

    @JsonProperty("avgVisitTime_pages")
    private List<Integer> avgVisitTime_pages;

    @JsonProperty("visits_endPage")
    private Long endPageVisits;

    @JsonProperty("questionResponses")
    private List<ResponseStatisticRest> questionResponses;

    public AnalyzeResponseRest(){
        this.avgVisitTime_pages = new ArrayList<>();
        this.pageVisits = new ArrayList<>();
        this.questionResponses = new ArrayList<>();
    }

    public Long getStartPageVisits() {
        return startPageVisits;
    }

    public void setStartPageVisits(Long startPageVisits) {
        this.startPageVisits = startPageVisits;
    }

    public int getAvgVisitTime_startPage() {
        return avgVisitTime_startPage;
    }

    public void setAvgVisitTime_startPage(int avgVisitTime_startPage) {
        this.avgVisitTime_startPage = avgVisitTime_startPage;
    }

    public List<Long> getPageVisits() {
        return pageVisits;
    }

    public void setPageVisits(List<Long> pageVisits) {
        this.pageVisits = pageVisits;
    }

    public List<Integer> getAvgVisitTime_pages() {
        return avgVisitTime_pages;
    }

    public void setAvgVisitTime_pages(List<Integer> avgVisitTime_pages) {
        this.avgVisitTime_pages = avgVisitTime_pages;
    }

    public Long getEndPageVisits() {
        return endPageVisits;
    }

    public void setEndPageVisits(Long endPageVisits) {
        this.endPageVisits = endPageVisits;
    }

    public List<ResponseStatisticRest> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(List<ResponseStatisticRest> questionResponses) {
        this.questionResponses = questionResponses;
    }
}
