import ATD
import sys

ATD.setDefaultKey("GEC_f5d029b602c7b98794329473432")

#sentences = ["Looking too the water. Fixing your writing typoss.",
#	"THis sentence is corrupt. Or is it? Who knows, really? Please test it out. Bruno is an good dog. Good dogs is rare."]

if len(sys.argv) < 2:
    print "Need input filename"
    sys.exit(1)

filename = sys.argv[1]
f = open(filename, "r")
sentences = f.readlines()

for s in sentences:
    errors = ATD.checkDocument(s)
    s_corr = s.rstrip()
    for error in errors:
        print "%s error for: %s **%s**" % (error.type, error.precontext, error.string)
        print "some suggestions: %s" % (", ".join(error.suggestions),)
        if error.suggestions:
            s_corr = s_corr.replace(error.string, error.suggestions[0])
    print s_corr

