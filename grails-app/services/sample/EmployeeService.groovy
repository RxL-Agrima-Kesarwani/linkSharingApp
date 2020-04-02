package sample

import grails.gorm.transactions.Transactional

import java.lang.reflect.Method

@Transactional
class EmployeeService {
    EmployeeService employeeService
   // def serviceMethod() {
       // println "service it is"
     //   Method execution in groovy
    //}
    def testMethod()
    {
        println "SDSDSfsg"

    }
}
