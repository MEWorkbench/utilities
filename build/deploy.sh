
echo OSS_SONATYPE_USER $OSS_SONATYPE_USER
echo OSS_SONATYPE_PASS $OSS_SONATYPE_PASS
echo GPG_PRIVATE_KEY   $GPG_PRIVATE_KEY


H2_HOME=$HOME/.deploym2
M2REPOSITORY=$H2_HOME/repository
cp build/oss.sonatype.settings.xml ${H2_HOME}/settings.xml

sed -i s/OSS_SONATYPE_USER/$OSS_SONATYPE_USER/g ${H2_HOME}/settings.xml
sed -i s/OSS_SONATYPE_PASS/$OSS_SONATYPE_PASS/g ${H2_HOME}/settings.xml

gpg -v --batch --import <(echo "$GPG_PRIVATE_KEY")
#mvn -Dmeworkbench.version=1.0.3 -Dmaven.repo.local=$M2REPOSITORY -Dproject.sign.skip=false -Dproject.scm.devcon=https://github.com/MEWorkbench/utilities.git deploy