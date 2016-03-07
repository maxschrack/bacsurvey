'use strict';
angular.module('bacsurveyApp')

  .factory('User', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'users/:userId', {}, {
      create: {method: 'POST'},
      update: {method: 'PUT', params: {userId: '@userId'}}
    });
  })
  .factory('Questionnaire', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'questionnaires/:action/:id', {}, {
      create: {method: 'POST', params: {}},
      update: {method: 'PUT', params: {}},
      getQuestionnaire: {method: 'GET', params: {action: 'get', id: '@id'}},
      readAllPerUser: {method: 'GET', params: {action: 'getAllPerUser', id: '@id'}, isArray: true}
    });
  })
  .factory('MetaPage', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'metaPages/:action/:questionnaireId', {}, {
      createStartPage: {method: 'POST', params: {action: 'newStartPage'}},
      createEndPage: {method: 'POST', params: {action: 'newEndPage'}},
      getStartPage: {method: 'GET', params: {action: 'getStartPage', questionnaireId: '@questionnaireId'}},
      getEndPage: {method: 'GET', params: {action: 'getEndPage', questionnaireId: '@questionnaireId'}},
      update: {method: 'PUT', params: {action: 'updatePage'}},
      delete: {method: 'DELETE', params: {action: 'deletePage', questionnaireId: '@questionnaireId'}}
    });
  })
  .factory('Page', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'pages/:action/:id', {}, {
      create: {method: 'POST', params: {}},
      update: {method: 'PUT', params: {}},
      readAllPerQuestionnaire: {method: 'GET', params: {action: 'getAllPerQuestionnaire', id: '@id'}, isArray: true}
    });
  })
  .factory('Question', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'questions/:action/:pageId', {}, {
      createOpenQuestion: {method: 'POST', params: {action: 'newOpenQuestion'}},
      createMultipleChoiceQuestion: {method: 'POST', params: {action: 'newMultipleChoiceQuestion'}},
      updateOpenQuestion: {method: 'PUT', params: {action: 'updateOpenQuestion'}},
      updateMultipleChoiceQuestion: {method: 'PUT', params: {action: 'updateMultipleChoiceQuestion'}},
      readAllPerPage: {method: 'GET', params: {action: 'readAllPerPage', pageId: '@pageId'}, isArray: true},
      delete: {method: 'DELETE', params: {action: 'delete', pageId: '@pageId'}}
    });
  })
  .factory('Participant', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'participants/:action/:id', {}, {
      create: {method: 'POST', params: {action: 'newParticipant'}}
    });
  })
  .factory('Answer', function ($resource, BACSURVEY_API) {
    return $resource(BACSURVEY_API + 'answers/:action/:id', {}, {
      create: {method: 'POST', params: {action: 'newAnswer'}}
    });
  });
