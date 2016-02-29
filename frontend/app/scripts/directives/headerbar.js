'use strict';
angular.module('bacsurveyApp')
    .directive('headerbar', function ($state, $stateParams, $route, $q, $cookies, $location, $filter, $rootScope, ErrorHandler, $log) {

        return {
            templateUrl: './views/directives/headerbar.html',
            link: function (vm) {

                vm.dropdownOpen = false;

                vm.credentials = undefined;


                activate();

                function activate() {
                    if ($rootScope.authToken) {
                        $location.path("/dashboard");
                    }
                }

                /**
                 * Logs out the current user
                 */
                vm.logout = function () {
                    AuthenticationService.logout();
                    $state.transitionTo($state.current, $stateParams, {
                        reload: true,
                        inherit: false,
                        notify: true
                    });
                };

                /**
                 * Forward to dashboard or landing page, depending on whether the user is logged in
                 */
                vm.showHome = function () {
                    if ($rootScope.loggedIn) {
                        $location.path('/dashboard');
                    } else {
                        $location.path('/landingPage')
                    }
                };

                /**
                 * Calls the authentication process. If successfull, retrieves current user from servers
                 */
                vm.login = function () {

                    if (!vm.credentials.username || !vm.credentials.password) {
                        ErrorHandler.showMessage($filter('translate')('ERROR_MESSAGES.MISSING_CREDENTIALS'));
                    }

                    var deferred = $q.defer();

                    UserService.authenticate(vm.credentials).$promise
                        .then(function (authenticationResult) {


                            deferred.resolve(authenticationResult);

                            var authToken = authenticationResult.token;

                            $rootScope.authToken = authToken;
                            $cookies.put('authToken', authToken);

                            var user = UserService.get(function () {
                                $rootScope.user = user;
                                $location.path('/dashboard');
                                $rootScope.loggedIn = true;
                                $rootScope.getUserData();
                            }, function (error) {
                                ErrorHandler.show(error);
                            });
                            vm.credentials = undefined;
                        })
                        .catch(function (error) {
                            if (error.status) {
                                ErrorHandler.showShort(error);

                            } else {
                                ErrorHandler.showMessageShort($filter('translate')('ERROR_MESSAGES.SERVICE_UNAVAILABLE'));
                            }
                            deferred.reject();
                        })
                };

                /**
                 * Forwards user to registration page
                 */
                vm.register = function () {
                    $location.path('/registration')
                };


                vm.$on('$routeChangeStart', function () {
                    vm.dropdownOpen = false;
                });

            }
        }
    });
