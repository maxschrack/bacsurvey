'use strict';
angular.module('bacsurveyApp')
    .directive('sidebar', function ($q, $state, $filter, $log, $rootScope, $location, ErrorHandler) {
        return {
            templateUrl: './views/directives/sidebar.html',
            restrict: 'E',
            link: function (vm) {

                vm.collapseAnalysis = false;

                vm.showHome = function () {
                    $location.path('/dashboard');
                };

                vm.showQuestionnaires = function () {
                    $location.path('/questionnaire');
                };

                vm.showParticipants = function () {
                    $location.path('/userAdmin');
                };

                vm.showResponseAnalysis = function () {
                  $state.go('responseAnalysis', {'questionnaireId': 1});
                };

                vm.showAnswerAnalysis = function () {
                  $state.go('answerAnalysis', {'questionnaireId': 1});
                };
            }
        };
    });
