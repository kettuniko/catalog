'use strict';
describe('Controllers', function () {

    beforeEach(module('myApp.controllers'));
    beforeEach(module('myApp.services'));

    describe('OptionsCtrl', function () {
        var scope, $httpBackend, ctrl, service;

        beforeEach(inject(function (_$httpBackend_, $rootScope, $controller, Import) {
            $httpBackend = _$httpBackend_;
            service = Import;
            scope = $rootScope.$new();
            ctrl = $controller('OptionsCtrl', {$scope: scope, Import: service});
        }));

        it('should success on import', function () {
            var expectedResult = [
                {name: "game"}
            ];
            doImportAndReturn(expectedResult);
            expect(scope.error).toEqual(false);
            expect(scope.importedItems.length).toEqual(expectedResult.length);
        });

        it('should fail on import', function () {
            doImportAndReturn(404)
            expect(scope.error).toEqual(true);
        });

        function doImportAndReturn(result) {
            expectImportAndReturn(result);
            scope.username = "test";
            scope.importGames();
            $httpBackend.flush();
        }

        function expectImportAndReturn(result) {
            $httpBackend.expectGET('http://localhost:8081/import/steam?username=test').respond(result);
        }
    });
});