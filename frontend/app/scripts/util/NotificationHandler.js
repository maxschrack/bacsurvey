
angular.module('bacsurveyApp').service('NotificationHandler', function(toaster) {
  'use strict';

  this.success= function(message) {
    toaster.pop({
      type:'success',
      title: 'Success',
      body: message,
      timeout: 3000
    });
  };

  this.successShort = function(message) {
    toaster.pop({
      type:'success',
      title: 'Success',
      body: message,
      timeout: 1000
    });
  };

  this.warn = function(message) {
    toaster.pop({
      type:'warning',
      title: 'Warning',
      body: message,
      timeout: 5000});
  };

  this.info = function(message) {
    toaster.pop({
      type:'note',
      title: 'Info',
      body: message,
      timeout: 10000});
  }
});
