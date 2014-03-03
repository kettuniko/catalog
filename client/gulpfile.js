// Include gulp
var gulp = require('gulp');

var jshint = require('gulp-jshint');
var sass = require('gulp-sass');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');
var jade = require('gulp-jade');
var livereload = require('gulp-livereload');
var bowerSrc = require('gulp-bower-src');
var server = livereload();

// Lint Task
gulp.task('lint', function () {
  return gulp.src('app/js/*.js')
      .pipe(jshint())
      .pipe(jshint.reporter('default'))
});

// Compile Our Sass
gulp.task('sass', function () {
  return gulp.src('app/scss/*.scss')
      .pipe(sass())
      .pipe(gulp.dest('./dist/css'))
});

// Concatenate & Minify JS
gulp.task('scripts', function () {
  return gulp.src('app/js/*.js')
      .pipe(concat('all.js'))
      .pipe(gulp.dest('dist'))
      .pipe(rename('all.min.js'))
      .pipe(uglify())
      .pipe(gulp.dest('dist'))
      .pipe(livereload())
});
 // Vendor scripts
gulp.task('vendor', function () {
  return gulp.src('app/bower_components/**/*.min.js')
      .pipe(concat('vendor.min.js'))
      .pipe(gulp.dest('dist/vendor'))
});
// Jade main
gulp.task('templates', function () {
  gulp.src('app/*.jade')
      .pipe(jade())
      .pipe(gulp.dest('./dist/'))
      .pipe(livereload())
});

// Jade partials
gulp.task('partials', function () {
  gulp.src('app/partials/*.jade')
      .pipe(jade())
      .pipe(gulp.dest('./dist/partials'))
      .pipe(livereload())
});


// Watch Files For Changes
gulp.task('watch', function () {
  gulp.watch('app/js/*.js', ['lint', 'scripts']);
  gulp.watch('app/scss/*.scss', ['sass']);
  gulp.watch('app/*.jade', ['templates']);
  gulp.watch('app/partials/*.jade', ['partials']);
});

// Default Task
gulp.task('default', ['lint', 'sass', 'vendor', 'scripts', 'watch', 'templates', 'partials']);
