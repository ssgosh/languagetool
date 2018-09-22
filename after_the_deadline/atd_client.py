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
    s_unicode = s.decode('utf-8')
    s_corr = s_unicode.encode('utf-8')
    s_corr = s_corr.rstrip()
    for error in errors:
        print >>sys.stderr, ("%s error for: %s **%s**. Description: %s" % (error.type,
                error.precontext, error.string,
                error.description)).encode('utf-8')
        if not "Diacritical" in error.description:
            print >>sys.stderr, ("some suggestions: %s" % (", ".join(error.suggestions),)).encode('utf-8')
            if error.suggestions:
                s_corr = s_corr.replace(error.string.encode('utf-8'),
                        error.suggestions[0].encode('utf-8'))
    print s_corr

