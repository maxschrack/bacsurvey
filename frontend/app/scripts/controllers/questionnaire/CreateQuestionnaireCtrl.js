angular.module('bacsurveyApp')

  .controller('CreateQuestionnaireCtrl', function ($window, $stateParams, $log, ErrorHandler, Questionnaire, MetaPage, NotificationHandler) {
    var vm = this;

    vm.questionnaire = {};
    vm.startPage = {};
    vm.endPage = {};
    vm.hasStartPage = false;
    vm.hasEndPage = false;
    vm.newStartPage;
    vm.newEndPage;
    vm.error = false;
    vm.errorMsg = '';

    vm.userId = 1;//$stateParams.id;

    vm.cancel = function () {
      $window.history.back();
    };


    vm.create = function () {
      vm.questionnaire.userId = vm.userId;
      vm.questionnaire.status = 'draft';

      // Create Questionnaire
      vm.newQuestionnaire = Questionnaire.create({'userId': vm.userId}, vm.questionnaire, function () {

        // create startPage
        if(vm.hasStartPage === 'true'){
          vm.startPage.questionnaireId = vm.newQuestionnaire.id;
          vm.newStartPage = MetaPage.createStartPage({}, vm.startPage, function () {

            // create EndPage
            if(vm.hasEndPage === 'true'){
              vm.endPage.questionnaireId = vm.newQuestionnaire.id;
              vm.newEndPage = MetaPage.createEndPage({}, vm.endPage, function () {
              }, function (error) {
                vm.error = true;
                vm.errorMsg = error;
              });
            }
          }, function (error) {
            vm.error = true;
            vm.errorMsg = error;
          });
        }else{
          // create EndPage
          if(vm.hasEndPage === 'true'){
            vm.endPage.questionnaireId = vm.newQuestionnaire.id;
            vm.newEndPage = MetaPage.createEndPage({}, vm.endPage, function () {
            }, function (error) {
              vm.error = true;
              vm.errorMsg = error;
            });
          }
        }
      }, function (error) {
        vm.error = true;
        vm.errorMsg = error;
      });

      // show error or success message
      if(vm.error){
        ErrorHandler.show(vm.errorMsg);
      }else{
        NotificationHandler.success('Questionnaire successfully created!');
      }
    }
  });
