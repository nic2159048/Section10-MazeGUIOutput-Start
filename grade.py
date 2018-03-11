# grade.py is a grading script that runs the given main class
# on all of the inputs (*.in files) in the given input directory.
# Does all this by copying the src/*.java files into TestingTemp/
# and running the tests in TestingTemp/.
#
# If the gradescope options is specified then the source
# code will be found in "/autograder/submission/".
# The script always generates a results.json file that could
# be used by gradescope.
#
# usage examples:
#   python grade.py PA1Main PublicTestCases --gradescope
#   python grade.py PA1Main PublicTestCases
#
# The assumption is that each main is operating on a given file.
# The input files in the specified input directory will be 
# given to the program.  Here are the operations performed:
#
#   javac JavaHelloWorld.java
#   java JavaHelloWorld PublicTestCases/file1.in
#   java JavaHelloWorld PublicTestCases/file2.in
#    ...
#
# Another assumption is that all of the source files are in the 
# default package and are in the src/ subdirectory.
# And the actual testing will happen in the testdir.
# Also assuming the correctness grade is out of 50 points.
import sys
import os
import json
import subprocess
import glob

#######################################
# global variables
tempdir = "TestingTemp/"
totalpoints = 50.0
gradescope_outfile = "results.json"


def getSubmissionSource(gradescope_flag):
#######################################
# like global variables should be set by user
    if gradescope_flag:
        return "/autograder/submission/"
    else:
        return "src/"


def cmdLineParse(argv):
#######################################
# usage examples:
#   python grade.py PA1Main PublicTestCases --gradescope
#   python grade.py PA1Main PublicTestCases
# Returns (main class name, testcase directory, gradescope flag)
    if len(sys.argv)<3:
        print("usage:")
        print("\tpython grade.py PA1Main PublicTestCases [--gradescope]")
        sys.exit()
    
    # return the main class name, testcase directory, and
    # whether gradescope should be run
    gradescope_flag = 0
    if len(sys.argv) >= 4:
        gradescope_flag = sys.argv[3]=="--gradescope"

    return (argv[1],argv[2],gradescope_flag)


def copySrcToTempAndCDThere(srcdir, tempdir):
#######################################
# Copying over all the source files into the testing
# directory, clean the directory, and then move into that directory.
# First parameter should be the source directory path.
# Second parameter should be the temporary directory.
    # If the temporary directory doesn't already exist make it.
    if not os.path.exists(tempdir):
        os.makedirs(tempdir)

    # clean the director of previous files
    try:
        os.remove(tempdir+"*.java")
    except OSError:
        pass

    # copy the source code over and move into directory
    os.system("cp "+srcdir+"/*.java "+tempdir)
    os.chdir (tempdir)

def execCommand(commandstr):
#######################################
# execute the given unix command line
# if successful then will return (0,stdout)
# if error then will return (cmdretcode,stderr)
# commandstr should be something like 'javac PA2Main.java'
# 
# reference for this code
# https://stackoverflow.com/questions/16198546/get-exit-code-and-stderr-from-subprocess-call
    retcode = 0
    try:
        output = subprocess.check_output(
            commandstr, stderr=subprocess.STDOUT, shell=True, universal_newlines=True)
    except subprocess.CalledProcessError as exc:
        output = exc.output
        retcode = exc.returncode

    return (retcode,output)


def truncateFloats(infile,outfile):
#######################################
# Will truncate all of the floating points to 2 decimal
# places and place the new version of the file in the
# given output file.
    execCommand("perl -pe 's/[-+]?\d*(?:\.?\d|\d\.)" \
                +"\d*(?:[eE][-+]?\d+)?/sprintf(\"%.2f\",$&)/ge' " \
                +infile+" > "+outfile)



def formatFloat(inval):
#######################################
# Want floats in our score output to all have 2 decimal points
    return float("%.2f" % inval)

def createTestRecord(mainclassname,infile,max_grade_per_test):
#######################################
# create a test record for each test
# run the program and redirect to the "out" file
    run_cmd = "java " + mainclassname + " " + infile + " > out"
    (run_retcode,run_output) = execCommand(run_cmd)

    # truncate the floats for generated out and expected
    basename = os.path.splitext(os.path.basename(infile))[0]
    truncateFloats("out","trunc_out")
    truncateFloats("../"+testdir+"/"+basename+".out", "trunc_expected")

    # do a diff with the generated output and the expected output
    diff_cmd = "diff -B -w trunc_out trunc_expected"
    (diff_retcode,diff_output) = execCommand(diff_cmd)
        
    # put together all the information in the test record
    if diff_retcode!=0:
        score = 0.0
        mesg = "Failed " + infile + " test.\n" \
               + "*********** OUTPUT: Actual output followed by expected.\n"
        print(mesg)
    else:
        score = max_grade_per_test
        mesg = "Passed " + infile + " test.\n"
        print(mesg)

    return { "score"       : formatFloat(score),
             "max_score"   : formatFloat(max_grade_per_test),
             "name"        : basename+'.in',
             "output"      : mesg + diff_output }


def compileProgram(mainclassname):
#######################################
# try to compile the program
# return (boolean indicating if succeeded, output message)

    # do the compile
    compile_cmd = 'javac '+mainclassname+'.java'
    (retcode,output) = execCommand(compile_cmd)

    # If compilation failed
    mesg_prefix = 'Compilation (' + compile_cmd + ')'
    if retcode!=0:
        print(mesg_prefix+' FAILED:\n'+output)
        return (False,mesg_prefix+' FAILED:\n'+output)
    else:
        print(mesg_prefix+' SUCCEEDED!\n')
        return (True,mesg_prefix+' SUCCEEDED!\n')


def runTests(mainclassname,testdir):
#######################################
# returns (list of test records,total_score,failed_at_least_once flag)

    # get a list of all the input files and max score per test
    input_files = glob.glob("../"+testdir+"/*.in")
    max_grade_per_test = totalpoints/float(len(input_files))
    #print("DEBUG: max_grade_per_test = ", max_grade_per_test)

    # Do all of the tests
    test_records = []
    total_score = 0.0
    failed_at_least_once = False
    for infile in input_files:
        test_rect = createTestRecord(mainclassname,infile,max_grade_per_test)
        failed_at_least_once = failed_at_least_once or (test_rect["score"]==0)
        total_score = total_score + test_rect["score"]
        test_records.append(test_rect)

    return (test_records, total_score, failed_at_least_once)



#######################################
# main python routine

# set everything up and cd into temporary directory
(mainclassname, testdir, gradescope_flag) = cmdLineParse(sys.argv)
srcdir = getSubmissionSource(gradescope_flag)
copySrcToTempAndCDThere(srcdir, tempdir)
# see https://gradescope-autograders.readthedocs.io/en/latest/specs/
# for format of json file that will come from results_dict
results_dict = {}

#### try to compile the program
(compile_succeeded,compile_msg) = compileProgram(mainclassname)
results_dict["output"] = compile_msg

#### If compilation failed then done, else do testing
if (compile_succeeded):
    (test_records, total_score, failed_at_least_once) = runTests(mainclassname,testdir)
    results_dict["score"] = formatFloat(total_score)
    results_dict["tests"] = test_records
else:
    results_dict["score"] = 0.0
    failed_at_least_once = True

# Testing output to stdout and the results.json file
print("score = "+str(results_dict["score"])+" out of "+str(totalpoints))
results_file = open(gradescope_outfile,"w")
json=json.dumps(results_dict, sort_keys=True, indent=4, separators=(',', ': '))
results_file.write(json)
results_file.close()

# Indicate whether there were any failures
if failed_at_least_once:
    sys.exit(1)
else:
    sys.exit(0)
