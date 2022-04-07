#! /usr/bin/python3 -u

import sys
import os
import subprocess
import inspect
import argparse

# Paths relative to this file (evaluate.py)
inputPath = "./input/"
refPath = "./"
srcPath = "../src/"
# Keep empty
classpath = ""
# Outputs files (don't change this, it's set in the main)
outErr = sys.stderr
outScore = sys.stdout
outVerbose = open(os.devnull,"w")
outputFilename = "result.txt"

################################################################################
def isEvalFile(filename) :
  return "censored" in filename
################################################################################

################################################################################
def compileCompiler() :
  global classpath
  for file in ["Compiler.java", "SaVM.java", "C3aVM.java", "NasmVM.java"] :
    if not os.path.isfile(srcPath+file) :
      print("Skipping compilation of %s"%file, file=outVerbose)
      continue
    package = file.lower().split('.')[0].replace('vm', '')
    if package in ["c3a", "nasm"] and not os.path.isdir(srcPath+package) :
      print("Skipping compilation of %s"%file, file=outVerbose)
      continue
    print("Compiling %s..."%file, end="", file=outVerbose)
    proc = subprocess.Popen("cd %s && javac %s %s %s"%(srcPath, "-cp " if len(classpath) > 0 else "", classpath, file), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    errMess = proc.stderr.read().decode('utf8')
    returnCode = proc.wait()
    if returnCode == 0 :
      print("Done", file=outVerbose)
    else :
      print("", file=outVerbose)
      print("ERROR !", file=outErr)
      print(errMess, file=outErr)
      exit(1)
    print("", file=outVerbose)
    classpath = "../xerces-2_12_1/*:%s"%srcPath
  classpath = ""
################################################################################

################################################################################
def deleteClasses() :

  for root, subdirs, files in os.walk("%s.."%srcPath) :
    if ".git" in root :
      continue
    for filename in files :
      if os.path.splitext(filename)[1] == ".class" :
        os.remove(root+"/"+filename)
        
  return classpath
################################################################################

################################################################################
def findClasspath() :
  global classpath

  if len(classpath) > 0 :
    return classpath

  for root, subdirs, files in os.walk("%s.."%srcPath) :
    if ".git" in root :
      continue
    for filename in files :
      if os.path.splitext(filename)[1] in [".class", ".jar"] :
        classpath += ("" if len(classpath) == 0 else ":") + root
        break

  classpath += ("" if len(classpath) == 0 else ":")+"../xerces-2_12_1/*"
  return classpath
################################################################################

################################################################################
def compiler() :
  return "java -classpath %s Compiler -v 2"%findClasspath()
################################################################################

################################################################################
def green(string) :
  return "\033[92m%s\033[0m"%string
################################################################################

################################################################################
def purple(string) :
  return "\033[95m%s\033[0m"%string
################################################################################

################################################################################
def red(string) :
  return "\033[91m%s\033[0m"%string
################################################################################

################################################################################
def changeExtension(filename, newExtension) :
  return os.path.splitext(filename)[0] + newExtension
################################################################################

################################################################################
def findInputFiles() :
# Ignore programs err*.l
  inputFiles = []
  for filename in os.listdir(inputPath) :
    if os.path.splitext(filename)[1] == ".l" :
      if len(filename) < 3 or filename[0:3] != "err" :
        inputFiles.append(filename)
  inputFiles.sort()
  return inputFiles
################################################################################

################################################################################
def deleteCompilationOutputs() :
  outputExtensions = [".exe", ".exeout", ".o", ".out", ".sa", ".saout", ".sc", ".ts", ".nasm", ".nasmout", ".pre-nasm", ".pre-nasmout", ".c3a", ".c3aout", ".fg", ".fgs", ".ig"]
  for filename in os.listdir(inputPath) :
    if os.path.splitext(filename)[1] in outputExtensions :
      os.remove(inputPath+filename)
################################################################################

################################################################################
def compileInputFiles(inputFiles) :
  for inputFile in inputFiles :
    print("Compiling %s..."%inputFile, end="", file=outVerbose)
    returnCode = subprocess.Popen("{} {}{}".format(compiler(), inputPath, inputFile), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).wait()
    if returnCode == 0 :
      print("Done", file=outVerbose)
    else :
      print("ERROR !", file=outErr)
  print("", file=outVerbose)
################################################################################

################################################################################
def getNewEvaluationResult(name) :
  return [name, {"correct" : [], "incorrect" : [], "notfound" : []}]
################################################################################

################################################################################
def evaluateDiff(inputFiles, extension, extensionRef, path, name) :
  evaluation = getNewEvaluationResult(name)

  for filename in inputFiles :
    producedFile = changeExtension(filename, extension)
    if not os.path.isfile(inputPath+producedFile) :
      evaluation[1]["notfound"].append(producedFile)
      continue
    
    ref = refPath+path+changeExtension(producedFile, extensionRef)
    if not os.path.isfile(ref) :
      print("ATTENTION : Fichier non trouvé : %s"%ref, file=sys.stderr)
      continue

    command = "diff {} {}{}".format(ref, inputPath, producedFile)
    res = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stdout.read().decode()
    if len(res.strip()) == 0 :
      evaluation[1]["correct"].append(producedFile)
    else :
      evaluation[1]["incorrect"].append(producedFile)

  return evaluation
################################################################################

################################################################################
def evaluateSa(inputFiles) :
  errs = []
  for filename in inputFiles :
    saFilename = inputPath+changeExtension(filename, ".sa")
    outFilename = inputPath+changeExtension(filename, ".saout")
    if not os.path.isfile(saFilename) :
      continue

    command = "java -classpath %s SaVM -sa %s > %s"%(findClasspath(), saFilename, outFilename)
    out = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stderr.read().decode()
    if len(out) != 0 :
      errs.append("ERROR %s for input %s : '%s'"%(inspect.stack()[0][3], filename, out))

  return evaluateDiff(inputFiles, ".saout", ".out", "out-ref/", "Execution de sa"), errs
################################################################################

################################################################################
def evaluateC3a(inputFiles) :
  errs = []
  for filename in inputFiles :
    c3aFilename = inputPath+changeExtension(filename, ".c3a")
    tsFilename = inputPath+changeExtension(filename, ".ts")
    outFilename = inputPath+changeExtension(filename, ".c3aout")
    if not os.path.isfile(c3aFilename) or not os.path.isfile(tsFilename) :
      continue

    command = "java -classpath %s C3aVM -c3a %s -ts %s > %s"%(findClasspath(), c3aFilename, tsFilename, outFilename)
    out = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stderr.read().decode()
    if len(out) != 0 :
      errs.append("ERROR %s for input %s : '%s'"%(inspect.stack()[0][3], filename, out))

  return evaluateDiff(inputFiles, ".c3aout", ".out", "out-ref/", "Execution du c3a"), errs
################################################################################

################################################################################
def evaluatePreNasm(inputFiles) :
  errs = []
  for filename in inputFiles :
    nasmFilename = inputPath+changeExtension(filename, ".pre-nasm")
    outFilename = inputPath+changeExtension(filename, ".pre-nasmout")
    if not os.path.isfile(nasmFilename) :
      continue

    command = "java -classpath %s NasmVM -nasm %s > %s"%(findClasspath(), nasmFilename, outFilename)
    out = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stderr.read().decode()
    if len(out) != 0 :
      errs.append("ERROR %s for input %s : '%s'"%(inspect.stack()[0][3], filename, out))

  return evaluateDiff(inputFiles, ".pre-nasmout", ".out", "out-ref/", "Execution du pre-nasm"), errs
################################################################################

################################################################################
def evaluateNasm(inputFiles) :
  errs = []
  for filename in inputFiles :
    nasmFilename = inputPath+changeExtension(filename, ".nasm")
    outFilename = inputPath+changeExtension(filename, ".nasmout")
    if not os.path.isfile(nasmFilename) :
      continue

    command = "java -classpath %s NasmVM -nasm %s > %s"%(findClasspath(), nasmFilename, outFilename)
    out = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stderr.read().decode()
    if len(out) != 0 :
      errs.append("ERROR %s for input %s : '%s'"%(inspect.stack()[0][3], filename, out))

  return evaluateDiff(inputFiles, ".nasmout", ".out", "out-ref/", "Execution du nasm"), errs
################################################################################

################################################################################
def evaluateExecutable(inputFiles) :
  errs = []
  for filename in inputFiles :
    nasmFilename = changeExtension(filename, ".nasm")
    objectFilename = changeExtension(filename, ".o")
    execFilename = changeExtension(filename, ".exe")
    outFilename = changeExtension(filename, ".exeout")
    if not os.path.isfile(inputPath+nasmFilename) :
      continue

    out = subprocess.Popen("cd {} && nasm -f elf -dwarf -g {}".format(inputPath+"..","input/"+nasmFilename), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stderr.read()
    if not os.path.isfile(inputPath+objectFilename) :
      errs.append("ERROR %s for input %s : '%s'"%(inspect.stack()[0][3], filename, out))
      continue
    out = subprocess.Popen("ld -m elf_i386 -o {}{} {}{}".format(inputPath,execFilename,inputPath,objectFilename), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stderr.read()
    if not os.path.isfile(inputPath+execFilename) :
      errs.append("ERROR %s for input %s : '%s'"%(inspect.stack()[0][3], filename, out))
      continue
    out = subprocess.Popen("{}{} > {}{}".format(inputPath,execFilename,inputPath,outFilename), shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).stderr.read()
    if not os.path.isfile(inputPath+outFilename) :
      errs.append("ERROR %s for input %s : '%s'"%(inspect.stack()[0][3], filename, out))
      continue

  return evaluateDiff(inputFiles, ".exeout", ".out", "out-ref/", "Execution du binaire"), errs
################################################################################

################################################################################
def printListElements(destination, elements, colorFunction, useColor, resultStr) :
  if len(elements) == 0 :
    return
  maxColumnSize = len(max(elements, key=len))
  for filename in elements :
    if useColor :
      print("\t{}".format(colorFunction(filename)), file=destination)
    else :
      print("\t{:{}} {}".format(filename, maxColumnSize+2, resultStr), file=destination)
################################################################################

################################################################################
def printEvaluationResult(destination, evaluationResult, useColor) :
  name = evaluationResult[0]
  correct = evaluationResult[1]["correct"]
  incorrect = evaluationResult[1]["incorrect"]
  notfound = evaluationResult[1]["notfound"]

  nbCorrect = len(correct)
  nbTotal = len(correct) + len(incorrect) + len(notfound)

  score = 0.0
  if nbTotal > 0 :
    score = 100.0*nbCorrect/nbTotal
  for dest, color in [(destination, useColor), (open(outputFilename, "a"), False)] :
    print("Évaluation de %s :"%name, file=dest)
    print("{}/{} correct ({:6.2f}%)".format(nbCorrect, nbTotal, score), file=dest)
    if nbCorrect+len(incorrect) > 0 :
      printListElements(dest, correct, green, color, "CORRECT")
      printListElements(dest, incorrect, purple, color, "INCORRECT")
      printListElements(dest, notfound, red, color, "NON-EXISTANT")
  return score
################################################################################

################################################################################
if __name__ == "__main__" :
  parser = argparse.ArgumentParser()
  parser.add_argument("--verbose", "-v", default=False, action="store_true",
    help="Verbose output (obsolete, verbose is default).")
  parser.add_argument("--silent", "-s", default=False, action="store_true",
    help="Less verbose output.")
  parser.add_argument("--noColors", default=False, action="store_true",
    help="Disable colors in output.")
  parser.add_argument("--clean", "-c", default=False, action="store_true",
    help="Clean input dir then exit.")
  parser.add_argument("--number", "-n", default=None, type=int,
    help="Restrict tests to n inputs.")
  parser.add_argument("--files", "-f", default=[], nargs="*",
    help="Specify input files.")
  args = parser.parse_args()

  args.verbose = not args.silent

  if args.verbose :
    outVerbose = outScore
  if args.clean :
      deleteCompilationOutputs()
      exit(0)

  with open(outputFilename, "w") as _ :
    pass

  inputFiles = args.files[:args.number]
  if len(inputFiles) == 0 :
    inputFiles = findInputFiles()[:args.number]
  deleteCompilationOutputs()

  # evalInputs = surprise inputs
  evalInputs = [filename for filename in inputFiles if isEvalFile(filename)]
  # normalInputs = inputs available to students
  normalInputs = [filename for filename in inputFiles if not isEvalFile(filename)]

  compileCompiler()
  compileInputFiles(inputFiles)

  scores = []
  names = []
  errors = []

  msg = "Diff de %s"
  dfSa = lambda files : (evaluateDiff(inputFiles, ".sa", ".sa", "sa-ref/", msg%"sa"), [])
  dfTs = lambda files : (evaluateDiff(inputFiles, ".ts", ".ts", "ts-ref/", msg%"ts"), [])
  dfC3a = lambda files : (evaluateDiff(inputFiles, ".c3a", ".c3a", "c3a-ref/", msg%"c3a"), [])
  dfPreNasm = lambda files : (evaluateDiff(inputFiles, ".pre-nasm", ".pre-nasm", "pre-nasm-ref/", msg%"pre-nasm"), [])
  dfNasm = lambda files : (evaluateDiff(inputFiles, ".nasm", ".nasm", "nasm-ref/", msg%"nasm"), [])

  for evalTarget in [
                     ("SA-DIFF", dfSa),
                     ("TS-DIFF", dfTs),
                     ("SA", evaluateSa),
                     ("C3A-DIFF", dfC3a),
                     ("C3A", evaluateC3a),
                     ("PRE-NASM-DIFF", dfPreNasm),
                     ("PRE-NASM", evaluatePreNasm),
                     ("NASM-DIFF", dfNasm),
                     ("NASM", evaluateNasm),
                     ("EXE", evaluateExecutable),
                    ] :
    names.append(evalTarget[0])
    res, err = evalTarget[1](normalInputs)
    scores.append(printEvaluationResult(outVerbose, res, not args.noColors))
    errors += err

  deleteClasses()

  if not args.noColors :
    print("Légende : {}  {}  {}".format(green("CORRECT"), purple("INCORRECT"), red("NON-EXISTANT")), file=outVerbose)

  if not args.verbose :
    print(" ".join(["%s%s"%(name," "*max(0,6-len(name))) for name in names]), file=outScore)
    print(" ".join(["%6.2f%s"%(scores[i]," "*max(0,len(names[i])-6)) for i in range(len(scores))]), file=outScore)

  errorsStr = ""

  if len(errors) > 0 :
    errorsStr = "%s\n%s"%((30*"-")+("EVALUATION ERRORS")+(30*"-"), "\n\n".join(errors))

  print(errorsStr)
  print(errorsStr, file=open(outputFilename, "a"))

  print("\nSauvegardé dans le fichier %s"%outputFilename)
################################################################################

