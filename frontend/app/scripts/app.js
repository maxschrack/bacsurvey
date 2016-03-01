'use strict';

/**
 * @ngdoc overview
 * @name bacsurveyApp
 * @description
 * # bacsurveyApp
 *
 * Main module of the application.
 */
angular
  .module('bacsurveyApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.router',
    'angular.filter',
    'toaster',
    'config',
    'ngDialog',
    'ui.bootstrap'
  ])
  .config(function ($stateProvider, $logProvider, $urlRouterProvider, DEBUG) {



    $logProvider.debugEnabled(DEBUG);

    $urlRouterProvider.otherwise('/dashboard');

    $stateProvider
      .state('landingpage', {
        url: '/landingpage',
        templateUrl: 'views/landingPage/landing-page.html',
        controller: 'LandingPageCtrl',
        controllerAs: 'vm'
      })
      .state('questionnaire', {
        url: '/questionnaire',
        templateUrl: 'views/questionnaire/questionnaire-administration.html',
        controller: 'QuestionnaireAdministrationCtrl',
        controllerAs: 'vm'
      })
      .state('createQuestionnaire', {
        url: '/createQuestionnaire',
        templateUrl: 'views/questionnaire/create-questionnaire.html',
        controller: 'CreateQuestionnaireCtrl',
        controllerAs: 'vm'
      })
      .state('editQuestionnaire', {
        url: '/users/:userId/editQuestionnaire/:questionnaireId',
        templateUrl: 'views/questionnaire/edit-questionnaire.html',
        controller: 'EditQuestionnaireCtrl',
        controllerAs: 'vm'
      });
  });
