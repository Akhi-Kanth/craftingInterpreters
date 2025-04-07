You can thing about the complier as a pipeline who's job is to organize the data and make it easier for the next stage of the of the complier to implement
    - front end of the pipeline is specific to the source language the program is written in
    - middle end of the pipleline is where the code can be stored in a intermediate representation (IR) that is not tied to either the source language and the machine
        - this means that it can support many languages
    - back end is concerned with the final architecture where the program will run.

bootstraping - coding the compiler in the same language

Front End

lexcial anaylsis (scanning) -> splits input file into tokens
parser - builds a parse tree or an abstact syntax tree based on the tokens (syntax trees, ASTs, or just trees)
static anaylsis -
    binding/resolution - each identifier, we find out where that name is defined and wire the two together (this is where scope comes out to play)
                       - if the the language is statcially typed, this is also where we throw a type error
                       - these values can be stored as attributes on the syntax tree itself (fills in extra empty nodes that were placed by the parser)
                       - it can also be stored in a symbole table on the side
                       - the most powerful method is to transform the tree into a entirely new data structure more directly expresses the semantics of the code.

Optimization

if we understand the progam, we can switch it out with a different program that makes the program simpler
    constant folding -  if some expression always evaluates to the exact same value, we can do the evaluation at compile time and replace the code for the expression with its result
        - pennyArea = 3.14159 * (0.75 / 2) * (0.75 / 2);
        - pennyArea = 0.4417860938


Back end

code generation - turns the IR into assembly-like instructions a CPU runs
    some compliers produce a virtual machine code, they produce code for an idealized machine called bytecode
    after this there is two different options,
        write a mini complier for each of the chips
        or write a virtual machine that emulates a hypothetical chip supporting your virtual architecture

    running bytecode is slower, but you get simplicity and portablity

Shortcuts and Alternate Routes

    single-pass compliers
        these work by producing output code directly in the parser without any trees or IR
        restrict the design of the language
        no intermediate data structures to store global information about the program
        as soon as you see some expression, you need to know enough to correctly compile it

        c was designed with this limitation because memory was extreamly valuable at that time

    tree-walk interpreters
        some languages begin excuting code right after parsing it to an AST (a tiny bit of static anaylsis)
        then the interpreter traverse the tree and evaluates each node as it goes

        common for little languges, not general purpose languages as it is slow

    transpliers
        bolting the front end fo your language so its IR becomes another source language
        source-to-source compiler or a transcompiler
        most transpliers work on higher - level languages (they normally go to c)
        Web browsers are the “machines” of today, and their “machine code” is JavaScript


    just-in-time compliation (JIT)
        not really a shortcut, but a more dangerous alpine scramble best reserved for experts
        converts source code (JS) or bytecode (JVM/CLR) into native machine code
        optimizes execution speed while maintaining platform independence


Compilers and Interpreters: what is the difference?
    one does not strictly imply the negation of the other
    compiling - implementation technique that involves translating a source language to some other—usually lower-level—form
        when we say a language implementation “is a compiler”, we mean it translates source code to some other form but doesn’t execute it. The user has to take the resulting output and run it themselves.
        when we say an implementation “is an interpreter”, we mean it takes in source code and executes it immediately. It runs programs “from source”.


---------------------------------------------------------------------------------------------------------------------
Repersenting Code
