/*global require*/
require.config({
    paths: {
        'angular': './lib/angular-1.5.3.min',
        'jquery': './lib/jquery-1.12.3.min',
        'jquery-ui': './lib/jquery-ui-1.11.4.min'
    },
    shim: {
        'angular': {
            exports: 'angular'
        }
    },
    deps: ['./bootstrap']
});