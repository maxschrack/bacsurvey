package bac.rest.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeResponseRest {

    @JsonProperty("visits_startPage")
    private Long startPageVisits;

    @JsonProperty("avgVisitTime_startPage")
    private Double avgVisitTime_startPage;

    @JsonProperty("visits_pages")
    private List<Long> pageVisits;

    @JsonProperty("avgVisitTime_pages")
    private List<Double> avgVisitTime_pages;

    @JsonProperty("visits_endPage")
    private Long endPageVisits;

    @JsonProperty("questionResponses")
    private List<ResponseStatisticRest> questionResponses;

    @JsonProperty("avgTime_question")
    private List<Double> avgTime_question;

    public AnalyzeResponseRest(){
        this.avgVisitTime_pages = new ArrayList<>();
        this.pageVisits = new ArrayList<>();
        this.questionResponses = new ArrayList<>();
        this.avgTime_question = new ArrayList<>();
    }

    public Long getStartPageVisits() {
        return startPageVisits;
    }

    public void setStartPageVisits(Long startPageVisits) {
        this.startPageVisits = startPageVisits;
    }

    public Double getAvgVisitTime_startPage() {
        return avgVisitTime_startPage;
    }

    public void setAvgVisitTime_startPage(Double avgVisitTime_startPage) {
        this.avgVisitTime_startPage = avgVisitTime_startPage;
    }

    public List<Long> getPageVisits() {
        return pageVisits;
    }

    public void setPageVisits(List<Long> pageVisits) {
        this.pageVisits = pageVisits;
    }

    public List<Double> getAvgVisitTime_pages() {
        return avgVisitTime_pages;
    }

    public void setAvgVisitTime_pages(List<Double> avgVisitTime_pages) {
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

    public List<Double> getAvgTime_question() {
        return avgTime_question;
    }

    public void setAvgTime_question(List<Double> avgTime_question) {
        this.avgTime_question = avgTime_question;
    }
}
