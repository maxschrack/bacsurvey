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
      getQuestionnaire: {method: 'GET', params: {userId: '@userId', questionnaireId: '@questionnaireId'}},
      readAllPerUser: {method: 'GET', params: {userId: '@userId'}, isArray: true}
    });
  })
  .factory('MetaPage', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'metaPages/:action/:questionnaireId', {}, {
      createStartPage: {method: 'POST', params: {action: 'newStartPage'}},
      createEndPage: {method: 'POST', params: {action: 'newEndPage'}},
      getStartPage: {method: 'GET', params: {action: 'getStartPage', questionnaireId: '@questionnaireId'}},
      getEndPage: {method: 'GET', params: {action: 'getEndPage', questionnaireId: '@questionnaireId'}},
      update: {method: 'PUT', params: {action: 'updatePage'}}

    });
  });
  /*.factory('Page', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'users/:userId/questionnaires/:questionnaireId/pages/:pageId', {}, {
      create: {method: 'POST', params: {userId: '@userId'}},
      update: {method: 'PUT', params: {userId: '@userId'}}
    });
  });*/
