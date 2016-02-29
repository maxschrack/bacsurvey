'use strict';
angular.module('bacsurveyApp')
    .directive('sidebar', function ($q, $state, $filter, $log, $rootScope, $location, ErrorHandler) {
        return {
            templateUrl: './views/directives/sidebar.html',
            restrict: 'E',
            link: function (vm) {

                vm.showHome = function () {
                    $location.path('/dashboard');
                };

                vm.showQuestionnaires = function () {
                    $location.path('/questionnaire');
                };

                vm.showParticipants = function () {
                    $location.path('/userAdmin');
                };

                vm.showAnalysis = function () {
                    $location.path('/universityAdmin');
                };
            }
        }
    });
