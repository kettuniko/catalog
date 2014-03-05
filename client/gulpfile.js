// Include gulp
var gulp = require('gulp');

var jshint = require('gulp-jshint');
var sass = require('gulp-sass');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');
var jade = require('gulp-jade');
var livereload = require('gulp-livereload');
var server = livereload();

// Lint Task
gulp.task('lint', function () {
    return gulp.src('app/js/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'))
});

// Compile Sass
gulp.task('sass', function () {
    return gulp.src('app/scss/*.scss')
        .pipe(sass())
        .pipe(gulp.dest('./dist/css'))
        .pipe(livereload())
});

// Bootstrap
gulp.task('bootstrap', function () {
    return gulp.src('app/bower_components/bootstrap/dist/css/bootstrap.min.css')
        .pipe(gulp.dest('./dist/css'))
});

// Images
gulp.task('images', function () {
    return gulp.src('app/img/*.gif')
        .pipe(gulp.dest('./dist/img'))
        .pipe(livereload())
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
    return gulp.src(['app/bower_components/**/*.min.js', 'app/bower_components/jquery/dist/jquery.min.js'])
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
    gulp.watch('app/img/*.gif', ['images']);
});

// Default Task
gulp.task('default', ['lint', 'sass', 'bootstrap', 'vendor', 'scripts', 'watch', 'templates', 'partials', 'images']);
