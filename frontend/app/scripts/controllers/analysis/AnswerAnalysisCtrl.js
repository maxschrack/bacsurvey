angular.module('bacsurveyApp')

  .controller('AnswerAnalysisCtrl', function ($window, $http, $stateParams, $log, ErrorHandler, Questionnaire, MetaPage, Page, Question, Participant, Answer, Log, Analysis, NotificationHandler, ngDialog) {

    var vm = this;

    vm.questionnaireId = $stateParams.questionnaireId;
    vm.responseAnalysis = {};
    vm.xxx = [];

    // read data
    vm.questionnaire = Questionnaire.getQuestionnaire({'id': vm.questionnaireId}, function () {
      vm.pages = Page.readAllPerQuestionnaire({'id': vm.questionnaireId},
        function (page) {
          // GO THROUGH PAGE LIST
          page.forEach(function (p) {
            p.questions = [];
            // GET ALL QUESTIONS PER PAGE
            Question.readAllPerPage({'pageId': p.id},
              function(question) {
                question.forEach(function (q) {
                  q.test = [];
                  p.questions.push(q);

                  if(q.type == 'mc') {
                    vm.generateChartForQuestion(q);
                  }else if(q.type == 'oq'){
                    q.oq_answers = [];
                    Analysis.getAnswerAnalysis({'id': q.id}, function(analysis){
                      q.oq_answers = analysis.answers;
                    }, function (error){
                      ErrorHandler.show(error);
                    })
                  }
                });
              }, function (error) {
                ErrorHandler.show(error);
              });

          });
        }, function (error) {
          ErrorHandler.show(error);
        });
    }, function(error){
      ErrorHandler.show(error);
    });

    vm.generateChartForQuestion = function(q){

      var title = 'questionChart_'+ q.pageId+'_'+ q.id;
      var label = ['Answers'];
      var data = [];
      var ykes = ['a'];
      var index = 1;

      Analysis.getAnswerAnalysis({'id': q.id}, function(analysis){
        q.test.push(analysis);
        analysis.answers.forEach(function(answ){
          var l = 'A'+index;
          if(answ.answer == ''){
            l = 'No Answer';
          }
          data.push({y: l, a: answ.counter});
          index = index + 1;
        });
        vm.createBarPlotBody(title, data, label, ykes);

      }, function (error){
        ErrorHandler.show(error);
      })
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

    vm.generateAnswerReport = function(){
      Analysis.generateAnswerReport({'id': vm.questionnaireId}, function(){
      }, function(error){
        ErrorHandler.show(error);
      });
    };
  });
