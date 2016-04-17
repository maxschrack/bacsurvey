angular.module('bacsurveyApp')

  .controller('ProcessAnalysisCtrl', function ($window, $http, $stateParams, $log, ErrorHandler, Questionnaire, MetaPage, Page, Question, Participant, Answer, Log, Analysis, NotificationHandler, ngDialog) {

    var vm = this;

    // variables for ordering and searching
    vm.sortType = 'name';
    vm.sortReverse = false;
    vm.searchTable = '';

    vm.questionnaireId = $stateParams.questionnaireId;
    vm.processAnalysis = {};
    vm.questions = {};

    // read data
    vm.questionnaire = Questionnaire.getQuestionnaire({'id': vm.questionnaireId}, function () {
      vm.questions = Questionnaire.getAllQuestions({'id': vm.questionnaireId}, function () {
        vm.processAnalysis = Analysis.getProcessModel({'id': vm.questionnaireId}, function(){
        }, function(error){
          ErrorHandler.show(error);
        });
      }, function(error){
        ErrorHandler.show(error);
      });
    }, function(error){
      ErrorHandler.show(error);
    });
  });
