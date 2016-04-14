/*global define*/
/*jslint plusplus: true*/
define([ 'angular' ], function (angular) {
    'use strict';
    return angular.module('tictactoeApp', [])
        .controller('tictactoeController', function ($scope, TicTacToe) {
            $scope.tictactoe = new TicTacToe();
        })
        .factory('TicTacToe', function ($http) {
            return function () {
                var tictactoe = this;
                tictactoe.handleStateResponse = function (state) {
                    tictactoe.state = state;
                };
                tictactoe.switchFirstTurn = function () {
                    $http.get('switchFirstTurn').success(tictactoe.handleStateResponse);
                };
                tictactoe.makeMove = function (rowIndex, columnIndex) {
                    $http.post('move', {row: rowIndex, column: columnIndex}).success(tictactoe.handleStateResponse);
                };
                $http.get('tictactoe').success(tictactoe.handleStateResponse);
            };
        });
});