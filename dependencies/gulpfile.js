var gulp = require('gulp'),
        concat = require('gulp-concat'),
        concatCss = require('gulp-concat-css'),
        uglify = require('gulp-uglify'),
        rename = require('gulp-rename'),
        autoprefixer = require('gulp-autoprefixer'),
        cleanCSS = require('gulp-clean-css'),
		clean = require('gulp-clean'),
		del = require('del'),
        browserSync = require('browser-sync').create();

var DEST = '../web/build/';


gulp.task('scripts', function () {
    return gulp.src('src/js/*.js')
            .pipe(concat('custom.js'))
            .pipe(gulp.dest(DEST + '/js'))
            .pipe(rename({suffix: '.min'}))
            .pipe(uglify())
            .pipe(gulp.dest(DEST + '/js'))
            .pipe(browserSync.stream());
});

gulp.task('site-css', function () {
	console.log("Adding change to the build file");
    return gulp.src('src/css/*.css')
            .pipe(concatCss('custom.css'))
            .pipe(gulp.dest(DEST + '/css'))
            .pipe(rename({suffix: '.min'}))
            .pipe(cleanCSS())
            .pipe(gulp.dest(DEST + '/css'))
});


gulp.task('watch', function () {
    // Watch .js files
    gulp.watch('src/js/*.js', ['scripts']);
    // Watch .scss files
    gulp.watch('src/css/*.css', ['site-css']);
});

// Default Task
gulp.task('build', ['scripts','site-css']);
gulp.task('default',['watch']);

