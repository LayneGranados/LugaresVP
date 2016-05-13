'use strict';
var gulp = require('gulp'),
  connect = require('gulp-connect'),
  jshint = require('gulp-jshint'),
  inject = require('gulp-inject'),
  wiredep = require('wiredep').stream,
  angularFilesort = require('gulp-angular-filesort');
var proxy = require('http-proxy-middleware');

var dir = {
  dev: {
    path: 'app',
    js: 'app/assets/js/**/*.js',
    devcss: 'app/assets/**/*.css',
    templates: 'app/templates/**/*'
  }
};

gulp.task('server-dev', function() {
  connect.server({
    root: dir.dev.path,
    livereload: true,
    port: 18080,
    middleware: function(connect, opt) {
      return [
        proxy('/REST/core', {
          target: 'http://localhost:3000/',
          changeOrigin: false,
          pathRewrite: {
            '^/REST/core' : '/'
          }
        })
      ]
    }

  });
});


gulp.task('lint', function() {
  return gulp.src([dir.dev.js])
    .pipe(jshint('.jshintrc'))
    .pipe(jshint.reporter('jshint-stylish'));
});

gulp.task('html', function() {
  gulp.src('./app/**/*.html')
    .pipe(connect.reload());
});

gulp.task('watch', function() {
  gulp.watch(dir.dev.css, ['inject', 'html']);
  gulp.watch([dir.dev.js, './Gulpfile.js'], ['lint', 'inject', 'html']);
  gulp.watch([dir.dev.templates,'app/index.html'], ['html']);
});


gulp.task('inject', function() {
  return gulp.src('index.html', {
      cwd: './app'
    })
    .pipe(inject(
      gulp.src([dir.dev.js]).pipe(angularFilesort()), {
        ignorePath: '/app/'
      }))
    .pipe(inject(
      gulp.src([dir.dev.devcss]), {
        ignorePath: '/app/'
      }))
    .pipe(wiredep({}))
    .pipe(gulp.dest('app/'));
});

gulp.task('default', ['server-dev', 'lint', 'inject', 'watch']);
