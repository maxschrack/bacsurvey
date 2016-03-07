'use strict';
angular.module('bacsurveyApp')
  .controller('QuestionnaireAdministrationCtrl', function ($filter, $q, $rootScope, $log, ErrorHandler, Questionnaire, NotificationHandler, $state) {

    var vm = this;

    // variables for ordering and searching
    vm.sortType = 'name';
    vm.sortReverse = false;
    vm.searchQuestionnaire = '';

    vm.userId = 1;

    // get the list of all questionnaires per user
    vm.questionnaireCollection = Questionnaire.readAllPerUser({'id': vm.userId},
      function () {
      },function (error) {
        ErrorHandler.show(error);
      }
    );


    // Button Links
    vm.createQuestionnaire = function () {
      $state.go('createQuestionnaire');
    };

    vm.showQuestionnaire = function (questionnaire) {
      $state.go('questionnaire', {'questionnaireId': questionnaire.id});
    };

    vm.editQuestionnaireData = function (questionnaire) {
      $state.go('editQuestionnaireData', {'userId': vm.userId, 'questionnaireId': questionnaire.id});
    };
    vm.editQuestionnaire = function (questionnaire) {
      $state.go('editQuestionnaire', {'userId': vm.userId, 'questionnaireId': questionnaire.id});
    }

    vm.runQuestionnaire = function (questionnaire){
      $state.go('runQuestionnaire', {'questionnaireId': questionnaire.id});
    }
  });
