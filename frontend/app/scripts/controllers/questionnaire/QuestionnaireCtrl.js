'use strict';
angular.module('bacsurveyApp')
  .controller('QuestionnaireCtrl', function ($http, $filter, $q, $rootScope, $log, ErrorHandler, Questionnaire, NotificationHandler, $state) {

    var vm = this;

    // variables for ordering and searching
    vm.sortType = 'name';
    vm.sortReverse = false;
    vm.searchQuestionnaire = '';
    vm.test = 'asdf';

    $http({
      method: 'GET',
      url: 'http://localhost:8080/users/1/questionnaires',
      params: {'userId': 1}
    }).then(function successCallback(response) {
      vm.test = 'sucess';
    }, function errorCallback(response) {
      vm.test = 'fail';
    });



    // get the list of all questionnaires per user
    /*vm.questionnaireCollection = Questionnaire.readAllPerUser({userId: 1},
      function () {

        vm.test = 'true';
      },function (error) {

        vm.test = error;
        ErrorHandler.show(error);
      }
    );*/


    // Button Links
    vm.createQuestionnaire = function () {
      $state.go('createQuestionnaire');
    }

    vm.showQuestionnaire = function (questionnaire) {
      $state.go('questionnaire', {'questionnaireId': questionnaire.id});
    };

    vm.editQuestionnaire = function (questionnaire) {
      $state.go('editQuestionnaire', {'questionnaireId': questionnaire.id});
    }
  });
