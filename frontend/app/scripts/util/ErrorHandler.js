'use strict';
angular.module('bacsurveyApp')
  .service('ErrorHandler', function (toaster) {
    'use strict';

    this.show = function (error) {
      if (error && error.data) {
        toaster.pop({
          type: 'error',
          title: 'Error',
          body: error.data.message,
          timeout: 10000
        });
      } else {
        toaster.pop({
          type: 'error',
          title: 'Error',
          body: 'Something went wrong',
          timeout: 10000
        });
      }

    };

    this.showShort = function (error) {
      if (error && error.data) {
        toaster.pop({
          type: 'error',
          title: 'Error',
          body: error.data.message,
          timeout: 3000
        });
      } else {
        toaster.pop({
          type: 'error',
          title: 'Error',
          body: 'Something went wrong',
          timeout: 3000
        });
      }

    };


    this.showMessage = function (errorMessage) {
      if (errorMessage) {
        toaster.pop({
          type: 'error',
          title: 'Error',
          body: errorMessage,
          timeout: 10000
        });
      } else {
        toaster.pop({
          type: 'error',
          title: 'Error',
          body: 'Something went wrong',
          timeout: 10000

        });
      }

    };

    this.showMessageShort = function (errorMessage) {
      if (errorMessage) {
        toaster.pop({
          type: 'error',
          title: 'Error',
          body: errorMessage,
          timeout: 3000
        });
      } else {
        toaster.pop({
          type: 'error',
          title: 'Error',
          body: 'Something went wrong',
          timeout: 3000
        });
      }

    };
  });
