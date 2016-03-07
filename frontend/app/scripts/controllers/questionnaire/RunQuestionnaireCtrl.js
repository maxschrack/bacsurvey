angular.module('bacsurveyApp')

  .controller('RunQuestionnaireCtrl', function ($window, $http, $stateParams, $log, ErrorHandler, Questionnaire, MetaPage, Page, Question, Participant, Answer, NotificationHandler, ngDialog) {

    var vm = this;

    vm.questionnaireId = $stateParams.questionnaireId;

    // DEFINE VARIABLES
    vm.questionnaire = {};
    vm.startPage = {};
    vm.pages = {};
    vm.questions = {};
    vm.endPage = {};
    vm.participant = {};
    vm.testAnswer = [];

    vm.answers = [];

    // COMPARISON VARIABLES
    vm.hasStartPage = false;
    vm.hasEndage = false;

    vm.showStartPage = false;
    vm.showPage = false;
    vm.showEndPage = false;
    vm.pageCounter = 0;
    vm.currentPage = -1;


    vm.questionnaire = Questionnaire.getQuestionnaire({'id': vm.questionnaireId}, function () {

      // GET START PAGE IF EXISTS
      vm.getStartPage(vm.questionnaire);

      // GET END PAGE
      vm.getEndPage(vm.questionnaire);

      // GET PAGES
      vm.getPages(vm.questionnaire);

      vm.createParticipant(vm.questionnaire);

    }, function(error){
      ErrorHandler.show(error);
    });

    /**
     * GET ALL QUESTIONS PER PAGE
     */
    vm.nextPage = function(){
      // SEND ANSWERS
      if(vm.currentPage != -1){
        // no questions at startPage
        vm.answers.forEach(function (a) {
          if(a.type == 'oq' || (a.type == 'mc' && !a.hasOwnProperty('mc'))){
            vm.createAnswer(a);
          }else if(a.type == 'mc' && a.mc.length != 0){
            var falseCounter = 0;
            var index = 0;

            // Save each selected Checkbox as new answer
            a.mc.forEach(function (mcAnswer){
              if(mcAnswer){
                vm.testAnswer.push({"participantId": a.participantId, "questionId":a.questionId, "answer": a.answerText[index]});
              }else{
                falseCounter = falseCounter + 1;
              }
              index = index + 1;
            });

            // Create answer for each checkbox
            vm.testAnswer.forEach(function (a){
              vm.createAnswer(a);
            });

            // save empty answer if participant has not chosen one
            if(falseCounter == a.mc.length){
              a.answer = '';
              vm.createAnswer(a);
            }
            vm.testAnswer = [];
          }
        });
      }

      vm.answers = [];
      vm.showPage = false;
      vm.currentPage = vm.currentPage+1;
      if(vm.currentPage < vm.pageCounter) {
        vm.questions = Question.readAllPerPage({'pageId': vm.pages[vm.currentPage].id},
          function () {
            // Prepare answer array
            vm.questions.forEach(function (question) {
              vm.answers.push(vm.generateAnswer(question));
            });
            vm.showPage = true;
          }, function (error) {
            ErrorHandler.show(error);
          });
      }else{
        // Questionnaire finished -> display endPage
        vm.showEndPage = true;
        vm.showPage = false;
      }
    };

    /**
     * CREATE AN ANSWER
     * @param answer
     */
    vm.createAnswer = function (answer){
      Answer.create({}, answer, function(){
        }, function(error){
          ErrorHandler.show(error);
        }
      );
    }

    /**
     * GET START PAGE OF QUESTIONNAIRE
     * @param questionnaire
     */
    vm.getStartPage = function(questionnaire){
      if (questionnaire.startPageId != null) {
        vm.startPage = MetaPage.getStartPage({'questionnaireId': questionnaire.id
        }, function () {
          // CHECK IF START PAGE IS NOT EMPTY
          if (!angular.equals({}, vm.startPage)) {
            vm.hasStartPage = true;
            vm.showStartPage = true;
          }
        }, function (error) {
          ErrorHandler.show(error);
        });
      }
    };

    /**
     * GET END PAGE OF QUESTIONNAIRE
     * @param questionnaire
     */
    vm.getEndPage = function(questionnaire){
      if (questionnaire.endPageId != null) {
        vm.endPage = MetaPage.getEndPage({'questionnaireId': questionnaire.id}, function () {
          if (!angular.equals({}, vm.endPage)) {
            vm.hasEndPage = true;
          }
        }, function (error) {
          ErrorHandler.show(error);
        });
      }
    };

    /**
     * GET ALL PAGES PER QUESTIONNAIRE
     * @param questionnaire
     */
    vm.getPages = function(questionnaire){
      vm.pages = Page.readAllPerQuestionnaire({'id': vm.questionnaireId}, function() {
        vm.pageCounter = vm.pages.length;
      }, function (error) {
        ErrorHandler.show(error);
      });
    }


    /**
     * CREATE A PARTICIPANT
     * @param questionnaire
     */
    vm.createParticipant = function(questionnaire){
      var participant = {};
      participant.email = "test@test.com";
      participant.ipAddress = "127.0.0.1";
      participant.password = "test_pw";
      participant.questionnaireId = questionnaire.id;
      vm.participant = Participant.create({}, participant, function(){

      }, function(error){
        ErrorHandler.show(error);
      });
    };

    /**
     * START QUESTIONNAIRE - BUTTON START PAGE
     */
    vm.startQuestionnaire = function(){
      vm.showStartPage = false;
      vm.nextPage();
    }

    // HELPER METHODS FOR UI

    /**
     * CREATE NEW ANSWER OBJECT
     * @param question
     * @returns answer object
     */
    vm.generateAnswer = function(question){
      var answer = {"participantId": vm.participant.id, "questionId": question.id, "answer": '', "type": question.type};
      if(question.type == 'mc' && question.isSingleChoice == false){
        answer.mc = [];
        answer.answerText = [];
        question.answers.forEach(function(mcAnswer){
          answer.mc.push(false);
          answer.answerText.push(mcAnswer.text);
        });

      }
      return answer;
    };

    /**
     * Return radio or checkbox
     * @param question
     */
    vm.getTypeForMC = function(question){
      if(question.isSingleChoice){
        return "radio";
      }
      return "checkbox";
    };
  }
);
