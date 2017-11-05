run: ; @ ./gradlew -q run
clean: ; @ ./gradlew -q --refresh-dependencies clean && rm -rf log/
download-lib: ; @ mkdir -p libs && cd libs && curl https://razaoinfo.dl.sourceforge.net/project/jfuzzylogic/jfuzzylogic/jFuzzyLogic.jar --output jFuzzyLogic.jar