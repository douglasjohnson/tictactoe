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
                tictactoe.cells = [];
                $http.get('tictactoe').success(function (tictactoeGame, status) {
                    var row,
                        gameboardRow,
                        column;
                    for (row = 0; row < tictactoeGame.gameboard.length; row++) {
                        gameboardRow = tictactoeGame.gameboard[row];
                        for (column = 0; column < gameboardRow.length; column++) {
                            tictactoe.cells.push(gameboardRow[column]);
                        }
                    }
                });
            };
        });
});