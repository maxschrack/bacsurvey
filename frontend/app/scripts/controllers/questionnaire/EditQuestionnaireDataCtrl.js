angular.module('bacsurveyApp')

  .controller('EditQuestionnaireDataCtrl', function ($window, $stateParams, $log, ErrorHandler, Questionnaire, MetaPage, NotificationHandler) {

    var vm = this;

    vm.userId = $stateParams.userId;
    vm.questionnaireId = $stateParams.questionnaireId;
    vm.hasStartPage = false;
    vm.hasEndPage = false;
    vm.startPage = {};
    vm.endPage = {};
    vm.optionsYesNo = [{"val": true, "name": "yes"}, {"val": false, "name": "no"}];



    // read data
    vm.questionnaire = Questionnaire.getQuestionnaire({'id': vm.questionnaireId}, function () {
      if (vm.questionnaire.startPageId != null){
        vm.startPage = MetaPage.getStartPage({'questionnaireId': vm.questionnaireId
        }, function () {
          if (!angular.equals({}, vm.startPage)) {
            vm.hasStartPage = true;
          }
        }, function (error) {
          ErrorHandler.show(error);
        });
      }
      if (vm.questionnaire.endPageId != null) {
        vm.endPage = MetaPage.getEndPage({'questionnaireId': vm.questionnaireId}, function () {
          if (!angular.equals({}, vm.endPage)) {
            vm.hasEndPage = true;
          }
        }, function (error) {
          ErrorHandler.show(error);
        });
      }
    }, function (error) {
      ErrorHandler.show(error);
      $window.history.back();
    });

    vm.cancel = function () {
      $window.history.back();
    };

    vm.update = function () {

      // Update Questionnaire
      vm.newQuestionnaire = Questionnaire.update({}, vm.questionnaire, function () {

        // edit startPage
        if(vm.hasStartPage){
          MetaPage.update({}, vm.startPage, function () {
          }, function (error) {
            vm.error = true;
            vm.errorMsg = error;
          });
        }

        // edit EndPage
        if(vm.hasEndPage){
          MetaPage.update({}, vm.endPage, function () {
          }, function (error) {
            vm.error = true;
            vm.errorMsg = error;
          });
        }
      }, function (error) {
        vm.error = true;
        vm.errorMsg = error;
      });

      // show error or success message
      if(vm.error){
        ErrorHandler.show(vm.errorMsg);
      }else{
        NotificationHandler.success('Questionnaire successfully edited!');
      }
    }
  }

);
