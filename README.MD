# Quick Accounting

[![Build Status](https://travis-ci.org/nenick/QuAcc.svg)](https://travis-ci.org/nenick/QuAcc) [![Coverage Status](https://coveralls.io/repos/nenick/QuAcc/badge.png)](https://coveralls.io/r/nenick/QuAcc)


task jacocoTestReport(type: JacocoReport) {
    description = "Generates Jacoco coverage reports: XML and HTML"
    group = "Reporting"
//                outputs.upToDateWhen { false }

    // use hidden configuration, for details look into JacocoPlugin.groovy
    jacocoClasspath = project.configurations['jacocoAnt']

    // exclude auto-generated classes and tests
    def fileFilter = ['**/R.class', '**/R$*.class', '**/BuildConfig.*', '**/Manifest*.*', '**/*Test*.*',
                      'android/**/*.*']
    def debugTree = fileTree(dir: "${project.buildDir}/intermediates/classes/debug", excludes: fileFilter)
    def mainSrc = "${project.projectDir}/src/main/java"

    sourceDirectories = files([mainSrc])
    classDirectories = files([debugTree])
    executionData = fileTree(dir: project.projectDir, includes: ['**/*.exec', '**/*.ec'])

    reports {
        xml {
            enabled = true
            destination = "${project.buildDir}/reports/jacoco/test/jacocoTestReport.xml"
        }
        csv.enabled false
        html {
            enabled = true
            destination = "${project.buildDir}/reports/jacoco"
        }
    }
}