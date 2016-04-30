/*global define*/
/*jslint plusplus: true*/
define([ 'angular', 'jquery', 'jquery-ui' ], function (angular, $) {
    'use strict';
    return angular.module('tictactoeApp', [])
        .controller('tictactoeController', function ($scope, TicTacToe) {
            $scope.tictactoe = new TicTacToe();
        })
        .factory('TicTacToe', function ($http) {
            return function () {
                var tictactoe = this;
                var dialog = $('#tictactoe-dialog-confirm').dialog({
                    resizable : false,
                    modal : true,
                    autoOpen : false,
                    buttons : {
                        Yes : function() {
                            dialog.close();
                            tictactoe.reset();
                        },
                        No : function() {
                            dialog.close();
                        }
                    }
                }).dialog('instance');
                tictactoe.handleStateResponse = function (state) {
                    tictactoe.state = state;
                };
                tictactoe.switchFirstTurn = function () {
                    $http.get('switchFirstTurn').success(tictactoe.handleStateResponse);
                };
                tictactoe.makeMove = function (rowIndex, columnIndex) {
                    $http.post('move', {row: rowIndex, column: columnIndex}).success(tictactoe.handleStateResponse);
                };
                tictactoe.reset = function () {
                    $http.get('reset').success(tictactoe.handleStateResponse);
                };
                tictactoe.playAgain = function () {
                    if (tictactoe.state.result) {
                        tictactoe.reset();
                    } else {
                        dialog.open();
                    }
                };
                $http.get('tictactoe').success(tictactoe.handleStateResponse);
            };
        });
});