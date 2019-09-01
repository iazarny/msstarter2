/*global angular */

/**
 * Services that persists and retrieves todos from localStorage or a backend API
 * if available.
 *
 * They both follow the same API, returning promises for all changes to the
 * model.
 */

angular.module('todomvc')
	.factory('todoStorage', function ($http, $injector) {
		'use strict';

		console.info("API  only ... No local storage");
		// Detect if an API backend is present. If so, return the API module, else
		// hand off the localStorage adapter
		return $http.get('http://rest-st-2:22222/api')
			.then(function () {
				return $injector.get('api');
			}, function () {
				return $injector.get('api');
			});
	})

	.factory('api', function ($resource) {
		'use strict';
		var store = {
			todos: [],

			api: $resource('/api/todos/:id', null,
				{
					update: { method:'PUT' }
				}
			),

			clearCompleted: function () {
				console.info("clearCompleted");
				var originalTodos = store.todos.slice(0);

				var incompleteTodos = store.todos.filter(function (todo) {
					return !todo.completed;
				});

				angular.copy(incompleteTodos, store.todos);

				return store.api.delete(function () {
					}, function error() {
						angular.copy(originalTodos, store.todos);
					});
			},

			delete: function (todo) {
				console.info("delete", todo);
				var originalTodos = store.todos.slice(0);

				store.todos.splice(store.todos.indexOf(todo), 1);
				return store.api.delete({ id: todo.id },
					function () {
					}, function error() {
						angular.copy(originalTodos, store.todos);
					});
			},

			get: function () {
				console.info("get");
				return store.api.query(function (resp) {
					console.info("get responce is", resp);
					angular.copy(resp, store.todos);
				});
			},

			insert: function (todo) {
				console.info("insert", todo);
				var originalTodos = store.todos.slice(0);

				return store.api.save(todo,
					function success(resp) {
						todo.id = resp.id;
						store.todos.push(todo);
					}, function error() {
						angular.copy(originalTodos, store.todos);
					})
					.$promise;
			},

			put: function (todo) {
				console.info("put", todo);
				return store.api.update({ id: todo.id }, todo)
					.$promise;
			}
		};

		return store;
	})

	.factory('localStorage', function ($q) {
		'use strict';

		var STORAGE_ID = 'todos-angularjs';

		var store = {
			todos: [],

			_getFromLocalStorage: function () {
				console.info(">>>>>>>> getFromLocalStorage");
				return JSON.parse(localStorage.getItem(STORAGE_ID) || '[]');
			},

			_saveToLocalStorage: function (todos) {
				console.info(">>>>>>>> _saveToLocalStorage");
				localStorage.setItem(STORAGE_ID, JSON.stringify(todos));
			},

			clearCompleted: function () {
				console.info(">>>>>>>> clearCompleted");
				var deferred = $q.defer();

				var incompleteTodos = store.todos.filter(function (todo) {
					return !todo.completed;
				});

				angular.copy(incompleteTodos, store.todos);

				store._saveToLocalStorage(store.todos);
				deferred.resolve(store.todos);

				return deferred.promise;
			},

			delete: function (todo) {
				console.info(">>>>>>>> delete");
				var deferred = $q.defer();

				store.todos.splice(store.todos.indexOf(todo), 1);

				store._saveToLocalStorage(store.todos);
				deferred.resolve(store.todos);

				return deferred.promise;
			},

			get: function () {
				console.info(">>>>>>>> get");
				var deferred = $q.defer();

				angular.copy(store._getFromLocalStorage(), store.todos);
				deferred.resolve(store.todos);

				return deferred.promise;
			},

			insert: function (todo) {
				console.info(">>>>>>>> insert");
				var deferred = $q.defer();

				store.todos.push(todo);

				store._saveToLocalStorage(store.todos);
				deferred.resolve(store.todos);

				return deferred.promise;
			},

			put: function (todo, index) {
				console.info(">>>>>>>> put");
				var deferred = $q.defer();

				store.todos[index] = todo;

				store._saveToLocalStorage(store.todos);
				deferred.resolve(store.todos);

				return deferred.promise;
			}
		};

		return store;
	});
