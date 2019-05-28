import './home.css';

import React from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col, Alert, UncontrolledCarousel } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';
import { getSession } from 'app/shared/reducers/authentication';

export interface IHomeProp extends StateProps, DispatchProps {}
const items = [
  {
    src: 'http://3.bp.blogspot.com/-zrmw9YeU3nw/Wg2wTcYeYaI/AAAAAAAADhI/XlG-aH_ScsQc4vGWqw4p-qbddsVBvky2gCK4BGAYYCw/s1600/DSC03985.JPG',
    altText: 'Slide 1',
    caption: 'Slide 1'
  },
  {
    src: 'https://2.bp.blogspot.com/-OjSptrI_zJs/V0cWQsPaiHI/AAAAAAAAGag/ykNb-sIKwG4Mbj_Ib19pEBA-EehR-pGyQCLcB/s1600/sedes-01.png',
    altText: 'Slide 2',
    caption: 'Slide 2'
  },
  {
    src:
      '22%20fill%3D%22%23555%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22277%22%20y%3D%22218.3%22%3EThird%20slide%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E',
    altText: 'Slide 3',
    caption: 'Slide 3'
  }
];

export class Home extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getSession();
  }

  render() {
    const { account } = this.props;
    return (
      <Row>
        <Col md="7">
          <h2>
            <Translate contentKey="home.title">Welcome, Java Hipster!</Translate>
          </h2>
          <p className="lead">
            <Translate contentKey="home.subtitle">This is your homepage</Translate>
          </p>
          {account && account.login ? (
            <div>
              <Alert color="success">
                <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
                  You are logged in as user {account.login}.
                </Translate>
              </Alert>
            </div>
          ) : (
            <div>
              <Alert color="warning">
                <Translate contentKey="global.messages.info.authenticated.prefix">If you want to </Translate>
                <Link to="/login" className="alert-link">
                  <Translate contentKey="global.messages.info.authenticated.link"> sign in</Translate>
                </Link>
                <Translate contentKey="global.messages.info.authenticated.suffix">
                  , you can try the default accounts:
                  <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)
                  <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).
                </Translate>
              </Alert>

              <Alert color="warning">
                <Translate contentKey="global.messages.info.register.noaccount">You do not have an account yet?</Translate>
                &nbsp;
                <Link to="/register" className="alert-link">
                  <Translate contentKey="global.messages.info.register.link">Register a new account</Translate>
                </Link>
              </Alert>
            </div>
          )}
          <h2>
            <Translate contentKey="home.link.homepage">JHipster homepage</Translate>
          </h2>
          <p>
            <Translate contentKey="home.link.chat">JHipster public chat room</Translate>
          </p>
          <p>
            <Translate contentKey="home.link.follow">follow @java_hipster on Twitter</Translate>
          </p>
          <p>
            <Translate contentKey="home.like">If you like JHipster, do not forget to give us a star on</Translate>{' '}
            <a href="https://github.com/SENA-CEET/D.W.B.H-CORPORATION" target="_blank" rel="noopener noreferrer">
              Github
            </a>
            !
          </p>
        </Col>
        <Col md="5" className="pad">
          <UncontrolledCarousel items={items} />
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home);
