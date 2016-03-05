angular.module('bacsurveyApp')

  .controller('EditQuestionnaireCtrl', function ($window, $stateParams, $log, ErrorHandler, Questionnaire, MetaPage, Page, Question, NotificationHandler, ngDialog) {

    var vm = this;

    vm.userId = $stateParams.userId;
    vm.questionnaireId = $stateParams.questionnaireId;
    vm.hasStartPage = false;
    vm.hasEndPage = false;
    vm.startPage = {};
    vm.endPage = {};
    vm.newQuestionType = "";

    vm.newOpenQuestion = {};
    vm.newMultipleChoiceQuestion = {"answers": [{"text": "", "id": 0}]};

    vm.optionQuestionType = [{"val": "OQ", "name": "Open Question"}, {"val": "MC", "name": "Multiple Choice"}];
    vm.optionsYesNo = [{"val": true, "name": "yes"}, {"val": false, "name": "no"}];
    vm.optionsValidationType = [{"val": "none", "name": "None"}, {"val": "email", "name": "E-Mail"}, {"val": "number", "name": "Number"}];

    // read data
    vm.questionnaire = Questionnaire.get({'userId': vm.userId, 'questionnaireId': vm.questionnaireId}, function () {

      // GET START PAGE
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

      // GET ALL PAGES PER QUESTIONNAIRE
      vm.pageCollection = Page.readAllPerQuestionnaire({'id': vm.questionnaireId},
        function (page) {
          // GO THROUGH PAGE LIST
          page.forEach(function (p) {
            p.questions = [];
            // GET ALL QUESTIONS PER PAGE
            Question.readAllPerPage({'pageId': p.id},
            function(question) {
              question.forEach(function (q) {
                p.questions.push(q);
              });
            }, function (error) {
              ErrorHandler.show(error);
            });
          });
      }, function (error) {
        ErrorHandler.show(error);
      });

      // GET END PAGE
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

    // UPDATE QUESTIONNAIRE DATA
    vm.updateQuestionnaireData = function () {
      vm.newQuestionnaire = Questionnaire.update({'userId': vm.userId, 'questionnaireId': vm.questionnaireId}, vm.questionnaire, function () {
        NotificationHandler.success('Questionnaire Data successfully edited!');
      }, function (error) {
        ErrorHandler.show(error);
      });
    };

    // UPDATE META PAGE DATA (START AND END PAGE)
    vm.updateMetaPage = function(metaPage) {
      MetaPage.update({}, metaPage, function () {
        NotificationHandler.success('Meta Page successfully edited!');
      }, function (error) {
        ErrorHandler.show(error);
      });
    };

    // DELETE META PAGE (START AND END PAGE)
    vm.deleteMetaPage = function (metaPage){
      var dialog = {};
      dialog.title = 'Delete';
      dialog.message = 'Are you sure you want to delete this page?';

      ngDialog.openConfirm({
        template: 'views/dialogs/confirm-dialog.html',
        data: dialog
      }).then(function () {
        MetaPage.delete({'questionnaireId': metaPage.id}, function () {
          NotificationHandler.success('Successfully deleted Page!');
          vm.startPage = {};
        }, function (error) {
          ErrorHandler.show(error);
        });

      }, function (reason) {
        // admin canceled the process
      });
    }

    // UPDATE Page DATA
    vm.updatePageData = function(page) {
      Page.update({}, page, function () {
        NotificationHandler.success('Page successfully edited!');
      }, function (error) {
        ErrorHandler.show(error);
      });
    };

    // UPDATE OPEN QUESTION
    vm.updateOpenQuestion = function(question){
      vm.test = Question.updateOpenQuestion({}, question,
        function (){
          NotificationHandler.success('Page successfully added!');
        }, function (error) {
          ErrorHandler.show(error);
        });

    }

    // UPDATE MULTIPLE CHOICE QUESTION
    vm.updateMultipleChoiceQuestion = function(question){
      vm.test = Question.updateMultipleChoiceQuestion({}, question,
        function (){
          NotificationHandler.success('Page successfully added!');
        }, function (error) {
          ErrorHandler.show(error);
        });

    }


    // CREATE NEW PAGE
    vm.addPage = function () {
      vm.newPage.questionnaireId = vm.questionnaireId;
      // TODO: POSITION

      // Create Questionnaire
      vm.test = Page.create({}, vm.newPage, function (data) {
        data.questions = [];
        vm.pageCollection.push(data);
        vm.showAddPageDiv = false;
        vm.newPage = {};
        NotificationHandler.success('Page successfully added!');
      }, function (error) {
        ErrorHandler.show(error);
      });
    };

    // CREATE NEW QUESTION
    vm.addMultipleChoiceQuestion = function (page) {
      vm.newMultipleChoiceQuestion.pageId = page.id;
      // TODO: POSITION

      vm.test = Question.createMultipleChoiceQuestion({}, vm.newMultipleChoiceQuestion, function (data) {
        page.questions.push(data);
        vm.showAddQuestionDiv = false;
        vm.newMultipleChoiceQuestion = {};
        vm.newMultipleChoiceQuestion = {"answers": [{"text": ""}]};
        vm.newQuestionType = "";
        NotificationHandler.success('Question successfully added!');
      }, function (error) {
        ErrorHandler.show(error);
      });
    };

    vm.addQuestion = function (page) {
      vm.newQuestion.pageId = page.id;
      // TODO: POSITION

      vm.test = Question.createOpenQuestion({}, vm.newQuestion, function (data) {
        page.questions.push(data);
        vm.showAddQuestionDiv = false;
        vm.newQuestion = {};
        vm.newQuestionType = "";
        NotificationHandler.success('Question successfully added!');
      }, function (error) {
        ErrorHandler.show(error);
      });
    };

    vm.deleteQuestion = function(questions, question){
      var dialog = {};
      dialog.title = 'Delete';
      dialog.message = 'Are you sure you want to delete this question?';

      ngDialog.openConfirm({
        template: 'views/dialogs/confirm-dialog.html',
        data: dialog
      }).then(function () {
        Question.delete({'pageId': question.id}, function () {
          var index = questions.indexOf(question);
          questions.splice(index, 1);
          NotificationHandler.success('Successfully deleted Question!');

        }, function (error) {
          ErrorHandler.show(error);
        });

      }, function (reason) {
        // admin canceled the process
      });
    }


    vm.addAnswer = function(question){
      var newAnswer = {"text":"", "id": 0};
      question.answers.push(newAnswer);
    }

    vm.removeAnswer = function(answers, answer){
      var index = answers.indexOf(answer);
      answers.splice(index, 1);
    }


    // ###################### SHOW NEW PAGE DIALOG * BUTTON
    vm.showAddPageDiv = false;
    vm.newPage = {};
    vm.showAddNewPageDialog = function(){
      vm.showAddPageDiv = !vm.showAddPageDiv;
    }

    // ###################### SHOW NEW PAGE DIALOG * BUTTON
    vm.newQuestion = {};
    vm.showAddQuestionDiv = false;
    vm.showAddNewQuestionDialog = function(){
      vm.showAddQuestionDiv = !vm.showAddQuestionDiv;
    }
  }

);
