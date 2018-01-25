# Section2-ExhaustiveSearch-WriteUp 

## Introduction 
Today's section will be an introduction to the algorithmic pattern of exhaustive
search and will help prepare you for PA3. The first part of the section will be
to generate all of the binary numbers for an n-bit number, in ascending order. 
The second part of the section will be to generate all of the possible bowling 
scores for a game with only three frames, treating the third frame as the tenth 
frame is typically treated.This section will also reinforce your skills relating 
to arrays. 

Exhaustive search is where every possible solution is iterated over, here is a 
link to the assigned reading assignment relative to exhaustive search: ???
//TODO: Is there a reading assignment?

We encourage paired programming during section, so please work through the section 
activity with the student sitting next to you. Remember, in section you can share 
code. For programming assignments you cannot.

## Setup 
Go to the course webpage, click resources, and then click on the Section 2
URL.  It will be the URL for accepting the github classroom assignment
for Section 1.

Then you need to edit your .travis.yml file in your section2 repository on 
github, and then clone and import the Section 2 gitrepo.
  * Click on .travis.yml in github.  Then put in your email and commit that change on
    github. If you do not do this, you will run into issues later with pushing and
    TravisCI. 
  
  * Click on the green "Clone or download" button on the right of the web page 
    and copy the provided URL.

  * Import your Section 2 repository into Eclipse.
    * open Eclipse 
    * File —> Import —> Git —> Projects from Git, Next, Clone URI, Next, paste 
      in repository   URL from github
    * Next, Select the master branch, Next, make the local destination 
      /home/username/eclipse-workspace/Section2-ExhaustiveSearch-yourgithubid.
    * Next, Import existing Eclipse projects, Next, Finish.

Now you are ready to start coding. 

## The Assignment
Part One: Exhaustively print out 0-2^N, where N is the number of bits, in ascending
order in binary.  	

Part Two: Exhaustively print out the bowling score possibilities for three frames, 
treating the third frame as the tenth frame for scoring. 

### Step 0
Copy the code below into main and run it to make sure it compiles and prints. 
This initiates the first TravisCI build.  

```
        System.out.println("Welcome to Section 2!");
```

Then commit and push your changes to github. Here is a reminder of the steps:
Right click on Section1Main.java --> Team --> Commit, move unstaged files 
that you want to commit into staged changes, put in a commit message, and 
then click commit and push.



### Part 1 - Binary Introduction 
For this section we are not reading in file from the command line, we are 
creating a class that generates and prints out in ascending order all of 
the binary representations of the numbers 0-2^N - 1, where N represents the 
number of bits. 

For example, if N = 2, then the decimal numbers 0-3 and the output would be:
```
00
01
10
11
``` 
#### Step 1 
Start by declaring an integer in main to represent N, the number of bits.

### Part Two - Bowling Itroduction 
Begin by reading over the scoring rules for bowling. These same scoring rules
will be implemented in PA3. 
