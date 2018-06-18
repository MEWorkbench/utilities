DEPLOYMENT_REPO_ID="test"


echo DEPLOY_FLAG: $1
if [ $1 = "master" ]; then
	DEPLOYMENT_REPO_URL="https://oss.sonatype.org/content/repositories/snapshots"
else
	MEWORKBENCH_VERSION=${MEWORKBENCH_VERSION}-SNAPSHOT
	DEPLOYMENT_REPO_URL="https://oss.sonatype.org/service/local/staging/deploy/maven2/"
fi
echo VERSION: $MEWORKBENCH_VERSION

#echo OSS_SONATYPE_USER $OSS_SONATYPE_USER
#echo OSS_SONATYPE_PASS $OSS_SONATYPE_PASS


H2_HOME=$HOME/.deploym2

M2REPOSITORY=$H2_HOME/repository
mkdir -p ${M2REPOSITORY}
cp build/oss.sonatype.settings.xml ${H2_HOME}/settings.xml

sed -i s,LOCAL_M2,$H2_HOME,g ${H2_HOME}/settings.xml
sed -i s/OSS_SONATYPE_USER/$OSS_SONATYPE_USER/g ${H2_HOME}/settings.xml
sed -i s/OSS_SONATYPE_PASS/$OSS_SONATYPE_PASS/g ${H2_HOME}/settings.xml
sed -i s/DEPLOYMENT_REPO_ID/$DEPLOYMENT_REPO_ID/g ${H2_HOME}/settings.xml

gpg -v --batch --import <(echo "$GPG_PRIVATE_KEY")
mvn -Dmeworkbench.version=${MEWORKBENCH_VERSION} -s ${H2_HOME}/settings.xml -Dmaven.repo.local=$M2REPOSITORY/ -Dproject.scm.devcon=https://github.com/MEWorkbench/utilities.git -DaltDeploymentRepository=${DEPLOYMENT_REPO_ID}::default::${DEPLOYMENT_REPO_URL} deploy