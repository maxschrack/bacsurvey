angular.module('bacsurveyApp')

  .controller('ResponseAnalysisCtrl', function ($window, $http, $stateParams, $log, ErrorHandler, Questionnaire, MetaPage, Page, Question, Participant, Answer, Log, Analysis, NotificationHandler, ngDialog) {

    var vm = this;

    vm.questionnaireId = $stateParams.questionnaireId;
    vm.responseAnalysis = {};
    data = [];

    // read data
    vm.questionnaire = Questionnaire.getQuestionnaire({'id': vm.questionnaireId}, function () {
      vm.responseAnalysis = Analysis.getResponseAnalysis({'id': vm.questionnaireId}, function(){
        vm.createResponsePerPageChart();
        vm.createAvgTimePerPageChart();
        vm.createAvgTimePerQuestionChart();
        vm.createNonResponsePerQuestionChart();
      }, function(error){
        ErrorHandler.show(error);
      })
    }, function(error){
      ErrorHandler.show(error);
    });


    // CHART: RESPONSE PER PAGE
    vm.createResponsePerPageChart = function (){
      var title = 'visitsPerPage';
      var label = ['Visits'];
      var data = [];
      var ykes = ['a'];
      var index = 1;

      data.push({y: 'Start Page', a: vm.responseAnalysis.visits_startPage});
      vm.responseAnalysis.visits_pages.forEach(function(amount){
        data.push({y: 'Page '+ index, a: amount});
        index = index +1;
      });
      data.push({y: 'End Page', a: vm.responseAnalysis.visits_endPage});

      return vm.createBarPlotBody(title, data, label, ykes);
    };

    // CHART: AVG TIME PER PAGE
    vm.createAvgTimePerPageChart = function (){
      var title = 'avgTimePerPage';
      var label = ['Time'];
      var data = [];
      var ykes = ['a'];
      var index = 1;

      data.push({y: 'Start Page', a: vm.round(vm.responseAnalysis.avgVisitTime_startPage)});
      vm.responseAnalysis.avgVisitTime_pages.forEach(function(time){
        data.push({y: 'Page '+ index, a: vm.round(time)});
        index = index +1;
      });

      return vm.createBarPlotBody(title, data, label, ykes);
    };

    // CHART: AVG TIME PER PAGE
    vm.createAvgTimePerQuestionChart = function (){
      var title = 'avgTimePerQuestion';
      var label = ['Time'];
      var data = [];
      var ykes = ['a'];
      var index = 1;

      vm.responseAnalysis.avgTime_question.forEach(function(time){
        data.push({y: 'Q '+ index, a: vm.round(time)});
        index = index +1;
      });

      return vm.createBarPlotBody(title, data, label, ykes);
    };

    // CHART: RESPONSE RATE PER QUESTION
    vm.createNonResponsePerQuestionChart = function (){
      var title = 'nonResponsePerQuestion';
      var label = ['Responses', 'Non Responses'];
      var data = [];
      var ykes = ['a', 'b'];
      var index = 1;

      vm.responseAnalysis.questionResponses.forEach(function(q){

        data.push({y: 'Q '+ index, a: q.counterResponse, b: q.counterNonResponse});
        index = index +1;
      });

      return vm.createBarPlotBody(title, data, label, ykes);
    };

    vm.round = function (num){
      return Math.round(num*100)/100;
    }

    /**
     * CREATES BAR PLOT BODY
     * @param title
     * @param data
     * @param label
     * @returns {{element: *, data: *, xkey: string, ykeys: string[], labels: *[]}}
     */
    vm.createBarPlotBody = function (title, data, labels, ykes){
      Morris.Bar({
        element: title,
        data: data,
        xkey: 'y',
        ykeys: ykes,
        labels: labels,
        barColors: ["#045FB4", "#FA5858"]
      });
    };

    vm.generateResponseReport = function(){
      Analysis.generateResponseReport({'id': vm.questionnaireId}, function(){
      }, function(error){
        ErrorHandler.show(error);
      });
    };
  });
