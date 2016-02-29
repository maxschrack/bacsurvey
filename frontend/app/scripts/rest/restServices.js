'use strict';
angular.module('bacsurveyApp')
  .factory('User', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'users/:userId', {}, {
      create: {method: 'POST'},
      update: {method: 'PUT', params: {userId: '@userId'}}
    });
  })
  .factory('Questionnaire', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'users/:userId/questionnaires/:questionnaireId', {}, {
      create: {method: 'POST', params: {userId: '@userId'}},
      update: {method: 'PUT', params: {userId: '@userId'}},
      readAllPerUser: {method: 'GET', params: {userId: '@userId'}, isArray: true}
    });
  });
  /*.factory('Page', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'users/:userId/questionnaires/:questionnaireId/pages/:pageId', {}, {
      create: {method: 'POST', params: {userId: '@userId'}},
      update: {method: 'PUT', params: {userId: '@userId'}}
    });
  });*/
